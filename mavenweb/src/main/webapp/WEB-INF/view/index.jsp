<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<h2>Hello World!</h2>
	<c:set var="test" value="JSTL Test" scope="page" />
	<c:out value="${test}" />
</body>
</html>
