
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        var isok = false;
        $.ajax({
			url: 'Login',
			type: 'POST',
			async: false,
			data: {"username": username,"password": password},
			success:function(data){
				if(data.length==7){
					$("button[type='submit']").text('用户名或密码错误');
				}else{
					isok = true;
				} 
			}
		});
        return isok;
    });
    $("input").change(function() {
    	 $("button[type='submit']").text('Sign me in');
    });

});