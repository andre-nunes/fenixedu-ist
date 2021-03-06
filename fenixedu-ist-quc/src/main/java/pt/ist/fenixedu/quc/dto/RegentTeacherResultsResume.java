/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST QUC.
 *
 * FenixEdu IST QUC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST QUC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST QUC.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.quc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.fenixedu.academic.domain.Professorship;

public class RegentTeacherResultsResume implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TeacherShiftTypeGroupsResumeResult> teacherShiftTypeGroupsResumeResults;
    private Professorship professorship;

    public RegentTeacherResultsResume(Professorship professorship) {
        setProfessorship(professorship);
    }

    @Override
    public int hashCode() {
        return getProfessorship().hashCode();
    }

    public void addTeacherShiftTypeGroupsResumeResult(TeacherShiftTypeGroupsResumeResult teacherShiftTypeGroupsResumeResult) {
        if (getTeacherShiftTypeGroupsResumeResults() == null) {
            setTeacherShiftTypeGroupsResumeResults(new ArrayList<TeacherShiftTypeGroupsResumeResult>());
        }
        getTeacherShiftTypeGroupsResumeResults().add(teacherShiftTypeGroupsResumeResult);
    }

    public void setTeacherShiftTypeGroupsResumeResults(
            List<TeacherShiftTypeGroupsResumeResult> teacherShiftTypeGroupsResumeResults) {
        this.teacherShiftTypeGroupsResumeResults = teacherShiftTypeGroupsResumeResults;
    }

    public List<TeacherShiftTypeGroupsResumeResult> getTeacherShiftTypeGroupsResumeResults() {
        return teacherShiftTypeGroupsResumeResults;
    }

    public List<TeacherShiftTypeGroupsResumeResult> getOrderedTeacherShiftResumes() {
        Collections.sort(getTeacherShiftTypeGroupsResumeResults(), new BeanComparator("shiftType"));
        return getTeacherShiftTypeGroupsResumeResults();
    }

    public void setProfessorship(Professorship professorship) {
        this.professorship = professorship;
    }

    public Professorship getProfessorship() {
        return professorship;
    }
}
