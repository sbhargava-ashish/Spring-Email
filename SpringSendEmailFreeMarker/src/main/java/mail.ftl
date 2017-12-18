<html>

<head>
        <title></title>
        <style></style>
</head>
<body>
    

</br>
<table border="1"	cellspacing="0" cellpadding="10" width="577">
<tr>
<td>

<table	cellspacing="0" cellpadding="0" width="567">
<tr>
<td>
<img src="cid:AvayaLogo.png">
</td>
<td>

</td>
<td>
<img src="cid:Inactive.png">

</td>
</tr>
</table>
 <table	cellspacing="0" cellpadding="0" width="567">
    <tr>
	<td>
      <p>Dear Administrator,</p>
      <p>You are receiving this email as one or more Gateway managed by you are currently inactive.
      <br>
      </td>
</tr>
 </table>
<br>

  <table border="1" cellspacing=0 cellpadding=0 width=567 >
   <tr>
    <td width=546 style='padding:6 0 6 15'>
    <table cellspacing=0 cellpadding=0 width=525 border=1 >
     <tr >
     
      <td width=135 style='padding:6 0 6 15'>
      <b>SEID</b>
      </td>
      
      <td width=135 style='padding:6 0 6 15;'>
      <b>Last Heartbeat Timestamp</b>
      </td>
         
     <td width=135 style='padding:6 0 6 15;'>
      <b> Customer </b>
      </td>
         
     </tr>
        
     <#list gateway as gate>
        <tr >
         <td width=135 style='padding:6 0 6 15'>
         <b>${gate.name}</b>
         </td>

         <td width=246 style='padding:6 0 6 15'>
         ${gate.hbTime}
         </td>

         <td width=144 style='padding:6 0 6 15'>
         ${gate.customer}
         </td>
        </tr>
     </#list> 
    </table>
    </td>
    
   </tr>

    
  </table>

</td>
</tr>
</table>
</body>
</html>  