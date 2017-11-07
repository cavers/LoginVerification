jQuery(document).ready(function() {
	$("#username").change(function() {
		$("#user").html("");
		$.ajax({
			url: 'register',
			type: 'GET',
			data: {"username": $(this).val()},
			success:function(date){
				var d = JSON.parse(date);
				if(d.result=="true"){
					$("#user").html("<b>用户名已经被使用！</b>");
				}
			}
		});
	});
	$("#button").click(function() {
		if($("#user").html()==""){
			$.ajax({
				url: 'register',
				type: 'POST',
				data: {"username": $("#username").val(),
					"password": $("#password").val(),
					"address": $("#address").val(),
					"cellphone": $("#cellphone").val(),
					"age": $("#age").val(),
					},
				success:function(){
					window.location.href = "index.html";
				}
			});
		}
	});
});