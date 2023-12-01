/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var firstName = "";
		var lastName = "";
		var email = "";
		var dType = "";
		var receipt = "";
		var anon = "";
		var list = "";
		var amount = "";
		
		$('.set-amount').autoGrow(0);
		
		/*
			if(isiPad || jQuery.browser.mobile){
				$('#team').hide()
				$('.team-mobile').show();	
			}else{
				$('#team').show()
				$('.team-mobile').hide();
			}
		*/
		
		//Set & Highlight Donation Amount
        const form = document.getElementById("donationForm");
        const customAmountInput = document.getElementById("customAmount");

        form.addEventListener("change", (event) => {
            const selectedLabel = form.querySelector("label.selected");
            if (selectedLabel) {
                selectedLabel.classList.remove("selected");
            }
            if (event.target.value === "custom") {
                customAmountInput.focus();
            }
        });

        customAmountInput.addEventListener("input", () => {
            const customAmount = customAmountInput.value;
            const customLabel = form.querySelector('input[value="custom"]').parentElement;
            customLabel.classList.toggle("selected", customAmount > 0);
        });
		
		
	

		
		$("input").on("change", function(){
			// $(".donation-box").css("height", "500px");
			
			firstName = $("#firstName").val();
			lastName = $("#lastName").val();
			email = $("#email").val();
			
			if ( $("#one-time").prop( "checked" ) ){
				dType = "One-Time";
			}
			if ( $("#monthly").prop( "checked" ) ){
				dType = "Monthly";
			}

		});