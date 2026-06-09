window.onload = function() {
    const username= localStorage.getItem('username');

     if (!username) {
        alert("Session expired. Please log in again.");
        window.location.href = "login.html";
        return;
    }
    fetch('/bank/transactions/'+username)
        .then(response => response.json())
        .then(data => {
            const transactionListContainer = document.getElementById('transactionListContainer');
            transactionListContainer.innerHTML = ''; // Clear previous content

            if (data.length === 0) {
                transactionListContainer.innerHTML = '<p style="color:#718096; text-align:center;">No passbook records found for this account.</p>';
                return;
            }
            data.forEach(transaction => {
                let amountColourClass="text-success";
                let amountSign="+";
                if(transaction.transactionType==="Withdrawn"||transaction.transactionType==="Transfer"){
                    amountColourClass="text-danger";
                    amountSign="-";
                }

                 const itemHTML = `
                <div class="transaction-item">
                    <div class="tx-left">
                        <p class="tx-title">${transaction.transactionType}</p>
                        <p class="tx-desc">${transaction.description}</p>
                    </div>
                    <div class="tx-amount ${amountColourClass}">
                        ${amountSign}₹${transaction.amount}
                    </div>
                </div>
            `;
                transactionListContainer.innerHTML += itemHTML;
            });
        })
        .catch(error => {
            console.error('Error fetching transactions:', error);
        });     
};      