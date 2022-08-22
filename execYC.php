<?php
	//$conn = mysqli_connect("13.124.173.165", "admin", "12345678", "db");
	include "../inc/dbinfo.inc";
	$conn = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD,'youtubedb');

	if(!$conn) {
		die("DB | Connect Error : " . mysqli_connect_error());
	}

	$youtubeID = $_GET["youtubeID"];
	
	exec("python hello.py ".$youtubeID);

	// 변수는 선언만 하면 오류 발생.
	$userID = "userID";
	$comment = "comment";
	$score = 0.0;

	//$statement = mysqli_prepare($conn, "SELECT userID, comment, score FROM YTtable WHERE youtubeID = ?");
	$statement = mysqli_prepare($conn, "SELECT userID, comment, score FROM ?");
	mysqli_stmt_bind_param($statement, "s", $youtubeID);
	mysqli_stmt_execute($statement);
	mysqli_stmt_bind_result($statement, $userID, $comment, $score);

	//$query = "SELECT userID, comment, score FROM YTtable WHERE youtubeID=" .$youtubeID;
    //$query = "SELECT userID, comment, score FROM YTtable WHERE youtubeID='".$youtubeID."'";
	//$result = mysqli_query($conn, $query);
	
	$data = array();

	while(mysqli_stmt_fetch($statement)) {
		$data["userID"] = $userID;
		$data["comment"] = $comment;
		$data["score"] = $score;
		$result[] = $data;
	}

	echo json_encode($result);
	mysqli_stmt_close($statement);
	mysqli_close($conn);
?>
