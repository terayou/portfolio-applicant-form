number = ;
tel = cellnnumberchack(number);
print(tel);S

function cellnumberchack(number){
	if(number.isNaN() && number.length　== 11){
		//電話番号にハイフンを自動挿入するメソッド		
		return number.slice(0 , 3) + '-' + number.slice(3 , 8) + '-' + number.slice(8 , number.length);		  
	}else{
		alert('数字以外で入力しないでください'); 
		return false;
		
	}
		
}