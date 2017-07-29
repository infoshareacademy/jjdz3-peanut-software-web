<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="subsection" type="peanut.medicine.web.icd9.classification.Subsection" required="true" %>

<div class="panel panel-default">
    <div class="panel-body">
        <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion2"
               href="#subsection_${subsection.hashCode()}">
                <div class="row">
                    <div class="col-xs-1"><strong>${subsection.code}</strong></div>
                    <div class="col-xs-11"><strong>${subsection.title}</strong></div>
                </div>
            </a>
        </h4>
    </div>
    <div id="subsection_${subsection.hashCode()}" class="panel-collapse collapse">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach var="mainCategory" items="${subsection.mainCategories}">
                    <tags:mainCategoryView mainCategory="${mainCategory}"/>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>