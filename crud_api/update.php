<?php
include 'connection.php';

$id = $_POST['id'];
$opmerking = $_POST['opmerking'];


$query = mysqli_query($con, "UPDATE CLIENT SET opmerking = '$opmerking' WHERE id = '$id' ");

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Updated Successfully';
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Updation Failed';
}

echo json_encode($response);
?>
