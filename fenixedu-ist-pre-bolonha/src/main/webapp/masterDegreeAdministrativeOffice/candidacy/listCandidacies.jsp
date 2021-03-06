<%--

    Copyright © 2013 Instituto Superior Técnico

    This file is part of FenixEdu IST Pre Bolonha.

    FenixEdu IST Pre Bolonha is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu IST Pre Bolonha is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu IST Pre Bolonha.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page isELIgnored="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>

<h2><strong><bean:message key="link.masterDegree.administrativeOffice.dfaCandidacy.listCandidacies" bundle="ADMIN_OFFICE_RESOURCES"/></strong></h2>

<fr:form action="/listDFACandidacy.do?method=listCandidacies">
	<fr:edit id="executionDegree"
			 name="candidacyBean"
			 type="org.fenixedu.academic.dto.administrativeOffice.candidacy.CreateDFACandidacyBean"
			 schema="candidacy.choose.executionDegree">
		<fr:destination name="degreePostBack" path="/listDFACandidacy.do?method=chooseDegreePostBack"/>
		<fr:destination name="degreeCurricularPlanPostBack" path="/listDFACandidacy.do?method=chooseDegreeCurricularPlanPostBack"/>		
		<fr:destination name="executionDegreePostBack" path="/listDFACandidacy.do?method=chooseExecutionDegreePostBack"/>				
		<fr:destination name="invalid" path="/listDFACandidacy.do?method=chooseExecutionDegreeInvalid"/>		
		<fr:layout name="tabular" >
				<fr:property name="classes" value="tstyle4"/>
		        <fr:property name="columnClasses" value="listClasses,,,"/>
		</fr:layout>
	</fr:edit>	
	<html:submit><bean:message key="button.submit" /></html:submit>
</fr:form>
<br/>
<br/>
<br/>
<logic:present name="candidacies">
	<logic:empty name="candidacies">
		<bean:message key="label.noCandidacies.found" bundle="ADMIN_OFFICE_RESOURCES" />
	</logic:empty>
	<logic:notEmpty name="candidacies">
		<bean:size id="numberOfCandidacies" name="candidacies" />
		<bean:message key="label.numberOfFoundCandidacies" bundle="ADMIN_OFFICE_RESOURCES"/>: <bean:write name="numberOfCandidacies" />
		<fr:view name="candidacies" schema="candidacy.show.list.candidady">
			<fr:layout name="tabular">
		        <fr:property name="classes" value="tstyle4"/>
		        <fr:property name="columnClasses" value=",,,acenter"/>
		        <fr:property name="linkFormat(view)" value="/listDFACandidacy.do?method=viewCandidacy&candidacyID=${externalId}"/>
				<fr:property name="key(view)" value="link.view"/>
				<fr:property name="sortBy" value="number"/>
		    </fr:layout>
		</fr:view>
	</logic:notEmpty>
</logic:present>