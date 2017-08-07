<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<templates:app>
    <jsp:body>
       <h4>From Server: ${helloMsg}</h4> 
    </jsp:body>
</templates:app>

