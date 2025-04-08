<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HelloWorld</title>
</head>
<body>
    <h1>홈페이지</h1>
	<p>Hello World</p>
	<div>
		<button onclick="jpaSelect()">JPA SELECT</button>
		<button onclick="jpaInsert()">JPA INSERT</button>
	</div>
</body>
</html>
<script>
	function jpaSelect() {
        fetch('/selectAll', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
		.then(response => response.json())
        .then(data => {
            alert("서버 응답: " + data);
        })
        .catch(error => {
            console.error("에러 발생:", error);
            alert("요청 실패");
        });
    }
	
	function jpaInsert() {
        fetch('/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.text())
        .then(data => {
            alert("서버 응답: " + data);
        })
        .catch(error => {
            console.error("에러 발생:", error);
            alert("요청 실패");
        });
    }
</script>
