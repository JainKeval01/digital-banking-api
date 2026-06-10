function refreshDashBoardData(){
 const username=sessionStorage.getItem("username");
    document.getElementById("username").innerText=username;

    fetch("/bank/balance/"+username)
    .then(res=>res.text())
    .then(balance=>{
        document.getElementById("balance").innerText=balance;
    }).catch(err=>{
        console.log("Error:",err);
        document.getElementById("balance").innerText="Error loading balance"
    });
}

window.onload=function(){
    refreshDashBoardData();
}

function logout() {
   sessionStorage.clear();

    alert("Logged out successfully");

    window.location.href = "login.html"; // back to login page
}

function deposit(){
    const amount=document.getElementById("depositAmount").value;
    const username =localStorage.getItem("username");

    if(!amount || amount<=0){
        alert("Enter valid amount");
        return;
    }

    let data={
        username:username,
        amount:Number(amount)
    }

    fetch("/bank/deposit",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },body:JSON.stringify(data)
    }).then(res=>res.text())
    .then(result=>{
        alert(result);
        refreshDashBoardData();
        document.getElementById("depositAmount").value="";
    }).catch(err=>{
        alert("Error connecting to server");
    });

}

function withdraw(){
    const amount=document.getElementById("withdrawAmount").value;
    const username =localStorage.getItem("username");

    if(!amount || amount<=0){
        alert("Enter valid amount");
        return;
    }

    let data={
        username:username,
        amount:Number(amount)
    }

    fetch("/bank/withdraw",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },body:JSON.stringify(data)
    }).then(res=>res.text())
    .then(result=>{
        alert(result);
        refreshDashBoardData();
        document.getElementById("withdrawAmount").value="";
    }).catch(err=>{
        alert("Error connecting to server");
    });
}
function transfer(){
    const toUser=document.getElementById("transferUser").value;
    const amount=document.getElementById("transferAmount").value;
    const username =localStorage.getItem("username");

    if(!amount || amount<=0){
        alert("Enter valid amount");
        return;
    }

    let data={
        fromUser:username,
        toUser:toUser,
        amount:Number(amount)
    }

    fetch("/bank/transfer",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },body:JSON.stringify(data)
    }).then(res=>res.text())
    .then(result=>{
        alert(result);
        refreshDashBoardData();
        document.getElementById("transferAmount").value="";
        document.getElementById("transferUser").value="";
    }).catch(err=>{
        alert("Error connecting to server");
    });
}

function viewTransactions(){
    window.location.href="transactionPage.html";
}
