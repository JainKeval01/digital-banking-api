document.getElementById("signUpForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission
    const username = document.getElementById("username").value;
    const mpin=document.getElementById("mpin").value;

    if(mpin.length !== 4 || isNaN(mpin)){
        alert("MPIN must be a 4-digit number.");
        return;
    }

    const userData = {
        name: username,
        mpin: mpin
    };

    fetch("http://localhost:8080/bank/signup",{
        method: "POST",
        headers:{
            "Content-Type":"application/json"
        },body:JSON.stringify(userData)
        }).then(res=>res.text())
        .then(data=>{
           if(data === "Registration Successful"){
                localStorage.setItem("username",username);
                alert("Sign Up successful! Redirecting to dashboard...");
                window.location.href = "dashboard.html";
           }else{
                alert("Ussername already exists. Please choose a different username.");
                return;
           }
        }               
    ).catch(error=>{
        console.error("Error:",error);
    });
});