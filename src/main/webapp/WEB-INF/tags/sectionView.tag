<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="section" type="peanut.medicine.web.icd9.classification.Section" required="true" %>

<div class="panel panel-default">
    <div class="panel-body">
        <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#section_${section.code}">
                <div class="row">
                    <div class="col-xs-1"><strong>${section.code}</strong></div>
                    <div class="col-xs-11"><strong>${section.title}</strong></div>
                </div>
            </a>
        </h4>
    </div>
    <div id="section_${section.code}" class="panel-collapse collapse">
        <div class="panel-body" id="accordion2">
            <ul class="list-group">
                <c:forEach var="subsection" items="${section.subsections}">
                    <tags:subsectionView subsection="${subsection}"/>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
