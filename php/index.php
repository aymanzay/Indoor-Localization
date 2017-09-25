<?php
	//login function
	function My_Login($username,$password)
	{
		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		$sql_command = "SELECT user_name, full_name, interest, update_frequency FROM tb_Users WHERE user_name='{$username}' and password = '{$password}'";
		$result = mysqli_query($con_db, $sql_command);
		$num_rows = mysqli_num_rows($result);
		if($num_rows>0){
			$row = mysqli_fetch_array($result);
			$username = $row[0];
			$fullname = $row[1];
			$interest = $row[2];
			$update_frequency = $row[3];
			echo 'Succeed;' . $username . ';' . $fullname . ';' . $interest . ';' . $update_frequency;
		}
		else{
			echo 'Failed';
		}
		mysqli_close($con_db);
	}

	function My_Confirm($username)
	{
    $con_db = mysqli_connect("localhost","root","root","hw1_db");
    if(mysqli_connect_errno($con_db)){
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    $sql_command = "SELECT user_name FROM tb_Users WHERE user_name='{$username}'";
		$result = mysqli_query($con_db, $sql_command);
		$num_rows = mysqli_num_rows($result);
		if($num_rows>0){
			echo 'Error';
		}
		else{
			echo 'True';
		}
		mysqli_close($con_db);
  }

  function My_Register($username,$password)
	{
		$fullname  = "Unknown";
		$frequency  = "10";
		$interests = "Sciences(Default)";
		$connected_mac = "c0:c1:c0:65:e3:b2";

		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql_command = "INSERT INTO tb_Users(user_name, password, full_name, interest, update_frequency, connected_mac) VALUES ('{$username}','{$password}','{$fullname}','{$interests}','{$frequency}','{$connected_mac}')";
		$result = mysqli_query($con_db, $sql_command);
		$num_rows = mysqli_num_rows($result);
		if($num_rows>0){
			echo 'False';
		}
		else{
			echo 'True';
		}

		mysqli_close($con_db);
	}

	function My_Update($username,$fullname,$interests,$frequency)
	{
		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql_command = "UPDATE tb_Users SET full_name='{$fullname}', interest='{$interests}', update_frequency='{$frequency}' WHERE user_name='{$username}'";
		$result = mysqli_query($con_db, $sql_command);

	}

	function My_Friends($username)
	{
		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql_command = "SELECT full_name, connected_mac, online FROM tb_Users WHERE user_name != '{$username}'";
		$result = mysqli_query($con_db, $sql_command);
		$json = mysqli_fetch_all ($result, MYSQLI_ASSOC);

		echo json_encode($json);
		mysqli_close($con_db);
	}

	function My_News($category)
	{
		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql_command = "SELECT category, URL FROM tb_News WHERE category='{$category}'";
		$result = mysqli_query($con_db, $sql_command);
		$json = mysqli_fetch_all ($result, MYSQLI_ASSOC);

		echo json_encode($json);
		mysqli_close($con_db);
	}

	function My_Locate($mac)
	{
		$con_db = mysqli_connect("localhost","root","root","hw1_db");
		if(mysqli_connect_errno($con_db)){
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}

		$sql_command = "SELECT building, floor FROM tb_Location WHERE mac='{$mac}'";
		$result = mysqli_query($con_db, $sql_command);
		$num_rows = mysqli_num_rows($result);

		if($num_rows>0){
			$row = mysqli_fetch_array($result);
			$building = $row[0];
			$floor = $row[1];
			echo 'Succeed;' . $building . ';' . $floor ;
		}
		else{
			echo 'Failed';
		}
		mysqli_close($con_db);
	}

  $method = $_POST['method'];
  switch ($method){
		case 'login':
			$username = $_POST['username'];
			$password = $_POST['password'];
			My_Login($username,$password);
			break;
		case 'confirm':
	    $username = $_POST['username'];
	    My_Confirm($username);
	    break;
    case 'register':
      $username = $_POST['username'];
			$password = $_POST['password'];
			My_Register($username,$password);
			break;
		case 'update':
	    $username = $_POST['username'];
	    $fullname = $_POST['fullname'];
		  $interests = $_POST['interests'];
		  $frequency = $_POST['frequency'];
		  My_Update($username,$fullname,$interests,$frequency);
		  break;
		case 'friends':
			$username = $_POST['username'];
			My_Friends($username);
			break;
		case 'news':
			$category = $_POST['category'];
			My_News($category);
			break;
		case 'locate':
			$mac = $_POST['mac'];
			My_Locate($mac);
			break;
    default:
      break;
    }
?>
