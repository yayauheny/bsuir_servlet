<html>
<head>
    <title>Student List</title>
</head>
<body>
<h1>Student List</h1>
<form action="hello-servlet" method="get">
    <label for="sortType">Choose sort of type:</label>
    <select id="sortType" name="sortType">
        <option value="name">Surname</option>
        <option value="averageScore">Average Score</option>
        <option value="course">Course</option>
    </select>
    <br>
    <br>
    <input type="submit" value="Sort">
</form>
</body>
</html>
