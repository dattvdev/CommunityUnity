// JavaScript to create the countdown timer
var countdown = 90; // Set the initial countdown time in seconds
var countdownDisplay = document.getElementById("countdown");

function updateCountdown() {
    countdown -= 1;
    countdownDisplay.textContent = formatTime(countdown);

    if (countdown <= 0) {
        countdownDisplay.textContent = "OTP had expired ";
        clearInterval(interval);
    }
}

// Update the countdown every 1000 milliseconds (1 second)
var interval = setInterval(updateCountdown, 1000);
function formatTime(seconds) {
    var minutes = Math.floor(seconds / 60);
    var remainingSeconds = seconds % 60;
    return "0" + minutes + ":" + remainingSeconds + " s";
}