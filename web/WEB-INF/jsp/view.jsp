<%@ page import="ru.javawebinar.webapp.model.MultiTextSection" %>
<%@ page import="ru.javawebinar.webapp.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.webapp.model.TextSection" %>
<%@ page import="ru.javawebinar.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/resume.css">
    <jsp:useBean id="resume" type="ru.javawebinar.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contectEntry" items="${resume.contacts}">
            <jsp:useBean id="contectEntry"
                         type="java.util.Map.Entry<ru.javawebinar.webapp.model.ContactType, java.lang.String>"/>

            <br><%=contectEntry.getKey().toHtml(contectEntry.getValue())%><br>
        </c:forEach>
    <p>

    <table cellpadding="8">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.webapp.model.SectionType, ru.javawebinar.webapp.model.Section>"/>
            <tr>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="ru.javawebinar.webapp.model.Section"/>

            <td><h3><a name="type.name">${type.title}</a></h3></td>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <td><h3><%=((TextSection) section).getContent()%></h3></td>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <td>
                        <ul>
                            <c:forEach var="item" items="<%=((MultiTextSection) section).getLines()%>">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    </tr>
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${empty org.homePage.url}">
                                        ${org.homePage.name}
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${org.homePage.url}">${org.homePage.name}</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="position" items="${org.positions}">
                            <jsp:useBean id="position" type="ru.javawebinar.webapp.model.Organization.Position"/>
                            <tr>
                                <td><%=HtmlUtil.formatDates(position)%></td>
                                <td><b>${position.title}</b><br>${position.description}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
    <button onclick="window.history.back()">ОК</button>
</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
