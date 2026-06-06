
document.getElementById("loginform").addEventListener("submit", function(event) {
    
    event.preventDefault(); 

    const username = document.getElementById("username").value;
    const mpin = document.getElementById("mpin").value;

    console.log("Username:", username);
    console.log("MPIN:", mpin);

    const formData = {
        username: username,
        mpin: mpin
    };
    fetch("http://localhost:8080/bank/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    }).then(response => response.text())
     .then(serverMessage=>{
        if(serverMessage === "Login successful"){
            localStorage.setItem("username",username);
            window.location.href = "dashboard.html";
        }else{
            alert(serverMessage);
        }
     }).catch(error => {
        console.error("Error during login:", error);
        alert("An error occurred during login. Please try again.");
     });
});
