<?php
include 'connection.php';
$query = mysqli_query($con, "SELECT Id,KantoorCode,Name,Address,Phone, Land, Opmerking FROM Client");
$data = array();
$qry_array = array();
$i = 0;

$total = mysqli_num_rows($query);


while ($row = mysqli_fetch_array($query)) {
  $data['Id'] = $row['Id'];
  $data['KantoorCode'] = $row['KantoorCode'];
  $data['Name'] = $row['Name'];
  $data['Address'] = $row['Address'];
  $data['Phone'] = $row['Phone'];
  $data['Land'] = $row['Land'];
  $data['Opmerking'] = $row['Opmerking'];
  $qry_array[$i] = $data;
  $i++;
}

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Loaded Successfully';
  $response['total'] = $total;
  $response['data'] = $qry_array;
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Loading Failed';
}

echo json_encode($response);
?>
