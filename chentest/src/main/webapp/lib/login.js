
function InitWindow()
{
	$("form0").set("send",{onComplete:function(obj,text){
		var data=JSON.decode(obj);
		$("resultmsg").set("text",data.message);
		if(data.error==0)
		{
		  window.location.href=$("successurl").value;
		}
	},
	onError:function(DataErr,Response){
		alert(DataErr);
	}});	
}

function Documentkeydown(event)
{
	var e=new Event(event);
	if(e.key=="enter")
		{
			if($("uid").value=="")
			{
				$("uid").focus();
			}else if($("pwd").value=="")
				{
					$("pwd").focus();
				}else if($("code").value=="")
					{
						$("code").focus();
					}else{
						Login();
					}
		
		}
}


function Login()
{
	//登录  
    var password = $("pwd").value;  
    RSAUtils.setMaxDigits(200);  
    //setMaxDigits(256);  
    
    var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);  

    var encrypedPwd = RSAUtils.encryptedString(key,password); 
    $("encrypedPwd").value = encrypedPwd;
	$("form0").send();
}

window.addEvent('domready', function(){ 
	InitWindow();
});