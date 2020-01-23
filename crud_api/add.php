<?php
include 'connection.php';

$OrderNumber = $_POST['OrderNumber'];
$ColliQty = $_POST['ColliQty'];
$RegistrationDate = $_POST['RegistrationDate'];
$response = array();
$query = mysqli_query($con, "INSERT INTO registration (OrderNumber, ColliQty, RegistrationDate) VALUES ('$OrderNumber','$ColliQty','$RegistrationDate')");

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Inserted Successfully';
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Insertion Failed';
}

echo json_encode($response);
?>
