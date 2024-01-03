import "./axios.min.js"
axios.get('/api/UserInfo')
    .then(function (response) {
        // 获取成功，处理返回的数据
        const userData = response.data;

        if (userData) {
            // 在页面上显示用户信息
            const userNameElement = document.getElementById('userName');
            // const phoneNumberElement = document.getElementById('phoneNumber');
            const userRoleElement = document.getElementById('userRole');
            let userRole = '';
            if (userData.userRole === 1){
                userRole = '护工'
            }else if(userData.userRole === 2){
                userRole = '管理员'
            }

            userNameElement.innerText = userData.userName;
            // phoneNumberElement.innerText = userData.phoneNumber;
            userRoleElement.innerText = userRole;
        }
    })
    .catch(function (error) {
        // 请求失败，处理错误
        alert("网络错误,请检查网络");
    });