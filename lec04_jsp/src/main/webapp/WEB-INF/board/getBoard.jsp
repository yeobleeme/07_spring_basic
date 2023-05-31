<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ${ board } --%>
	<div align="center">
		<h3>게시글 상세 조회</h3>
		<hr />
		<form action="updateBoard" method="post">
			<input type="hidden" name="seq" value="${ board.getSeq() }"/>
			<table border="1">
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input type="text" name="title" value="${ board.getTitle() }"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left"><input type="text" name="writer" size="20" value="${ board.getWriter() }" readonly/></td>
				</tr>
				<tr>
					<td bgcolor="orange">글내용</td>
					<td align="left"><textarea name="content" cols="30" rows="10">${ board.getContent() }</textarea></td>
				</tr>
				<tr>
					<td bgcolor="orange">등록일</td>
					<td align="left"><fmt:formatDate  value="${ board.getCreateDate() }" pattern="yyyy.MM.dd"></fmt:formatDate></td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td align="left">${ board.getCnt() }</td>
				</tr>				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정"/>
					</td>
				</tr>
			</table>
		</form>
		<hr />
		<a href="insertBoard">등록</a>&nbsp;&nbsp;&nbsp;
		<a href="deleteBoard?seq=${ board.getSeq() }">삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getBoardList">목록</a>
	</div>
</body>
</html>
















