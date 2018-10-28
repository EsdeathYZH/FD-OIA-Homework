function createRequest(){
		var xmlHttp;
		if (window.XMLHttpRequest){
		    xmlHttp = new XMLHttpRequest();
		}
		else if (window.ActiveXObject)
		{
		    try{
		        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		    }
		    catch (e)
		    {
		        try{
		            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		        }
		        catch (e) {}
		    }
		}
		return xmlHttp;
	}

function request(xmlHttp,data,url)
{
	xmlHttp.open("POST",url, false);
	xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');	
	xmlHttp.send(data);

	//alert(xmlHttp.responseText);
}

function encodeFullTxt(str){
	str = str.replace(/·/g,"&middot;");
	/*str = str.replace(/&#38;/g,"%26");
    str = str.replace(/&#39;/g,"%27" );
    str = str.replace(/&quot;/g,"%22")*/
	return str;
}

function decodeFullTxt(str){
	str = str.replace(/&middot;/g,'·');
	return str;
}