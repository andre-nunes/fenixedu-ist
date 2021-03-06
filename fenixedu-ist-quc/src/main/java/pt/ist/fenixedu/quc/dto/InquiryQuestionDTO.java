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

import pt.ist.fenixedu.quc.domain.InquiryAnswer;
import pt.ist.fenixedu.quc.domain.InquiryCheckBoxQuestion;
import pt.ist.fenixedu.quc.domain.InquiryQuestion;
import pt.ist.fenixedu.quc.domain.InquiryTextBoxQuestion;
import pt.ist.fenixedu.quc.domain.QuestionAnswer;
import pt.ist.fenixedu.quc.domain.StudentInquiryRegistry;

public class InquiryQuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private StudentInquiryRegistry studentInquiryRegistry;
    private InquiryQuestion inquiryQuestion;
    private QuestionAnswer questionAnswer;
    private String responseValue;
    private boolean isVisible;
    private String[] conditionValues;

    public InquiryQuestionDTO(InquiryQuestion inquiryQuestion, StudentInquiryRegistry studentInquiryRegistry) {
        setInquiryQuestion(inquiryQuestion);
        setStudentInquiryRegistry(studentInquiryRegistry);
        setConditionOptions(studentInquiryRegistry);
    }

    public InquiryQuestionDTO(InquiryQuestion inquiryQuestion, InquiryAnswer inquiryAnswer) {
        setInquiryQuestion(inquiryQuestion);
        setVisible(true);
        setQuestionAnswer(inquiryAnswer != null ? inquiryAnswer.getQuestionAnswer(inquiryQuestion) : null);
        if (getQuestionAnswer() != null) {
            setResponseValue(getQuestionAnswer().getAnswer());
        }
    }

    public InquiryQuestionDTO(InquiryQuestion inquiryQuestion) {
        setInquiryQuestion(inquiryQuestion);
        setVisible(true);
    }

    private void setConditionOptions(StudentInquiryRegistry studentInquiryRegistry) {
        setVisible(getInquiryQuestion().isVisible(studentInquiryRegistry));
        setConditionValues(getInquiryQuestion().getConditionValues(studentInquiryRegistry));
    }

    public void setInquiryQuestion(InquiryQuestion inquiryQuestion) {
        this.inquiryQuestion = inquiryQuestion;
    }

    public InquiryQuestion getInquiryQuestion() {
        return inquiryQuestion;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    public String getFinalValue() {
        if (getResponseValue() != null) {
            if (getInquiryQuestion() instanceof InquiryCheckBoxQuestion) {
                if (getResponseValue().equals("on")) {
                    return "1";
                }
                return getResponseValue();
            }
            if (getInquiryQuestion() instanceof InquiryTextBoxQuestion) {
                return getResponseValue().isEmpty() ? null : getResponseValue();
            }
        }
        return getResponseValue();
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setConditionValues(String[] conditionValues) {
        this.conditionValues = conditionValues;
    }

    public String[] getConditionValues() {
        return conditionValues;
    }

    public void setStudentInquiryRegistry(StudentInquiryRegistry studentInquiryRegistry) {
        this.studentInquiryRegistry = studentInquiryRegistry;
    }

    public StudentInquiryRegistry getStudentInquiryRegistry() {
        return studentInquiryRegistry;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }
}
