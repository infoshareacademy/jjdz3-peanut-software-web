<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="mainCategory" type="peanut.medicine.web.icd9.classification.MainCategory" required="true" %>

<div class="panel panel-default">
    <div class="panel-body">
        <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion3"
               href="#subsection_${mainCategory.hashCode()}">
                <div class="row">
                    <div class="col-xs-1"><strong>${mainCategory.code}</strong></div>
                    <div class="col-xs-11"><strong>${mainCategory.title}</strong></div>
                </div>
            </a>
        </h4>
    </div>
    <div id="subsection_${mainCategory.hashCode()}" class="panel-collapse collapse">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach var="detailedCategory" items="${mainCategory.detailedCategories}">
                    <tags:detailedCategoryView detailedCategory="${detailedCategory}"/>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>