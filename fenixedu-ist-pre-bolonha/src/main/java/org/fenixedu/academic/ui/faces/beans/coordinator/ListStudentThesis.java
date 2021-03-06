/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Pre Bolonha.
 *
 * FenixEdu IST Pre Bolonha is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Pre Bolonha is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Pre Bolonha.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.ui.faces.beans.coordinator;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.fenixedu.academic.domain.DegreeCurricularPlan;
import org.fenixedu.academic.domain.MasterDegreeThesisDataVersion;
import org.fenixedu.academic.dto.InfoMasterDegreeThesisDataVersionWithGuidersAndRespAndThesis;
import org.fenixedu.academic.predicate.IllegalDataAccessException;
import org.fenixedu.academic.service.services.exceptions.FenixServiceException;
import org.fenixedu.academic.service.services.exceptions.NonExistingServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.FenixFramework;

/**
 *
 * @author - Shezad Anavarali (shezad@ist.utl.pt)
 *
 */
public class ListStudentThesis {

    private static final Logger logger = LoggerFactory.getLogger(ListStudentThesis.class);

    private String degreeCurricularPlanID;

    public ListStudentThesis() {
    }

    public String getDegreeCurricularPlanID() {
        return degreeCurricularPlanID;
    }

    public void setDegreeCurricularPlanID(String degreeCurricularPlanID) {
        this.degreeCurricularPlanID = degreeCurricularPlanID;

        ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute(
                "degreeCurricularPlanID", degreeCurricularPlanID);

    }

    public List getMasterDegreeThesisDataVersions() {

        try {
            return run(degreeCurricularPlanID);
        } catch (IllegalDataAccessException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        } catch (FenixServiceException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }

        return null;

    }

    private static List run(String degreeCurricularPlanID) throws FenixServiceException {

        DegreeCurricularPlan degreeCurricularPlan = FenixFramework.getDomainObject(degreeCurricularPlanID);

        List masterDegreeThesisDataVersions =
                MasterDegreeThesisDataVersion.readActiveMasterDegreeThesisDataVersions(degreeCurricularPlan);

        if (masterDegreeThesisDataVersions == null || masterDegreeThesisDataVersions.isEmpty()) {
            throw new NonExistingServiceException("error.exception.masterDegree.nonExistingMasterDegreeThesis");
        }

        CollectionUtils.transform(masterDegreeThesisDataVersions, new Transformer() {
            @Override
            public Object transform(Object arg0) {
                MasterDegreeThesisDataVersion masterDegreeThesisDataVersion = (MasterDegreeThesisDataVersion) arg0;
                return InfoMasterDegreeThesisDataVersionWithGuidersAndRespAndThesis
                        .newInfoFromDomain(masterDegreeThesisDataVersion);
            }
        });

        return masterDegreeThesisDataVersions;
    }

}