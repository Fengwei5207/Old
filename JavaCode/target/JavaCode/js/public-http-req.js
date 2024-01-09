import './axios.min.js'

axios.get('/api/UserInfo')
    .then(function (response) {
        // 获取成功，处理返回的数据
        const userData = response.data;
        if (userData === 'unLogin\n') {
            window.location.href = '/login.html';
        }
        if (userData) {
            // 在页面上显示用户信息
            const userNameElement1 = document.getElementById('userName1');
            const userNameElement2 = document.getElementById('userName2');
            // const phoneNumberElement = document.getElementById('phoneNumber');
            const userRoleElement1 = document.getElementById('userRole1');
            const userRoleElement2 = document.getElementById('userRole2');
            let userRole = '';
            if (userData.userRole === '1') {
                userRole = '护工'
            //  取消显示审核员工信息
                const preUserElement = document.getElementById("preUser")
                preUserElement.style.display = 'none'
                // 取消发送公告按钮
                try {
                    const newNoticeBtnElement = document.getElementById('newNoticeBtn')
                    // console.log(newNoticeBtnElement)
                    newNoticeBtnElement.style.display = 'none'
                }catch (e) {
                    console.log(e)
                }


            } else if (userData.userRole === '2') {
                userRole = '管理员'
            }
            // console.log(userData)
            userNameElement1.innerText = userData.userName;
            userNameElement2.innerText = "Hello "+userData.userName;
            // phoneNumberElement.innerText = userData.phoneNumber;
            userRoleElement1.innerText = userRole;
            userRoleElement2.innerText = userRole;
        }
    })
    .catch(function (error) {
        // 请求失败，处理错误
        // alert("网络错误,请检查网络");
        location.reload();
    });


const logoutButton = document.getElementById('logoutButton');

logoutButton.addEventListener('click', function () {
    // 加按钮点击后的逻辑
    axios.get('/api/logout')
        .then((res) => {
            console.log(res)
            if (res.data === 'success') {
                window.location.href = '/login.html';
            }
        })
});

