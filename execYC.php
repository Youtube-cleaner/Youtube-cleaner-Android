<?php
	//$conn = mysqli_connect("13.124.173.165", "admin", "12345678", "db");
	include "../inc/dbinfo.inc";
	$conn = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD,'youtubedb');

	if(!$conn) {
		die("DB | Connect Error : " . mysqli_connect_error());
	}

    echo "DB | Connected";

	$youtubeID = $_GET["youtubeID"];
    echo "GET | youtubeID = $youtubeID";
	
	exec("python hello.py ".$youtubeID);
    echo "Python | exec";

	// 변수는 선언만 하면 오류 발생.
	$userID = "userID";
	$comment = "comment";
	$score = 0.0;

	$statement = mysqli_prepare($conn, "SELECT userID, comment, score FROM YTtable WHERE youtubeID = ?");
	mysqli_stmt_bind_param($statement, "s", $youtubeID);
	mysqli_stmt_execute($statement);
	mysqli_stmt_bind_result($statement, $userID, $comment, $score);

	//$query = "SELECT userID, comment, score FROM YTtable WHERE youtubeID=" .$youtubeID;
    //$query = "SELECT userID, comment, score FROM YTtable WHERE youtubeID='".$youtubeID."'";
	//$result = mysqli_query($conn, $query);
	echo "DB | query exec";
	
	$data = array();

	while(mysqli_stmt_fetch($statement)) {
		$data["userID"] = $userID;
		$data["comment"] = $comment;
		$data["score"] = $score;
	}

	/*
	if(mysqli_num_rows($result)>0){
		while ($row = mysqli_fetch_array($result)) {
			array_push($data, array('userID'=>$row['userID'], 'comment'=>$row['comment'], 'score'=>$row['score']));
		}
		$json = json_encode(array("YTtable"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
		echo $json;
	} else {
		echo "DB | SQL query Error : " . mysqli_error($conn);
	}
	*/

	echo json_encode($data);

	mysqli_close($conn);
?>