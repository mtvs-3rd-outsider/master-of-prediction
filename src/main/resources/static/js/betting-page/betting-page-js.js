var login = true;
var sideSelect = true;
var activeSelect = 0;
window.onload = function () {
    createSideMain();
    sideSelectBuy();
    graphChart();
}

function sideSelectBuy() {
    sideSelect = true;
    const buyButton = document.querySelector('.buy-item button');
    const sellButton = document.querySelector('.sell-item button');
    buyButton.style.color='#8D7CBB';
    buyButton.style.borderBottom='3px solid #8D7CBB';
    sellButton.style.borderBottom='none';
    sellButton.style.color='gray';
    createSideMain();

}

function sideSelectSell() {
    const buyButton = document.querySelector('.buy-item button');
    const sellButton = document.querySelector('.sell-item button');

    buyButton.style.color='gray';
    sellButton.style.color='#8D7CBB';
    sellButton.style.borderBottom='3px solid #8D7CBB';
    buyButton.style.borderBottom='none';
    sideSelect = false;
    createSideMain();

}

function graphSettingButton(value){
    const buttons= document.querySelectorAll('.graph_setting-content-button');
    buttons.forEach(button => {
        button.style.backgroundColor = 'white';
        button.style.setProperty('--md-sys-color-primary', '#6750a4');

    });
    buttons[value].style.backgroundColor='#F0C8D0';
    buttons[value].style.setProperty('--md-sys-color-primary', 'white');
}

function graphSetting() {
    const popupSetting = document.querySelector('.popup_setting-content');
    if (popupSetting.style.display === 'block') {
        popupSetting.style.display = 'none';
    } else {
        popupSetting.style.display = 'block';
    }
}

function popupCancle() {
    const popupSetting = document.querySelector('.popup_setting-content');
    popupSetting.style.display = 'none';
}

function createSideMain() {
            // 부모 요소 선택
            const sideMainContent = document.querySelector('.side_main-container');

            let battingModalStr;
            if(login===true){
                if(sideSelect===true){
            battingModalStr=` <button class="batting-buy-modal" onclick="buyModal()">구매</button>`;
        }else{
            battingModalStr=` <button class="batting-sell-modal" onclick="sellModal()">판매</button>`;
        }
    }else{
        battingModalStr=` <button class="batting-login-modal" onclick="loginModal()">로그인</button>`;
    }
    // 새로운 HTML 문자열 생성
    const sideHtml = `
    <div class="${sideSelect ? 'side_buy-content' : 'side_sell-content'}">
     <ul>
                    <li>예측</li>
                    <li>
                        <div class="side_buy-yesno-li">
                            <button class="yesno-button" onclick="yesButton()">YES</button>
                            <button class="yesno-button" onclick="noButton()">NO</button>
                        </div>
                    </li>
                    <li>구매포인트</li>
                    <li>
                        <div class="side-point-input">
                            <button>+</button>
                            <input type="number" id="inputPoint" onchange="calculateTotal()" step="10">
                            <button>-</button>
                        </div>
                    </li>
                    <li>
                        `+battingModalStr+`
                    </li>
                    <li>
                        <div class="side-sum-content">
                            합계 <span id="totalPoint">0<span>Point</span></span>
                        </div>
                    </li>
                    <li>
                        <div class="side-return-content">
                            잠재수익률 <span id="returnPoint">0<span>%</span></span>
                        </div>
                    </li>
                </ul>
    </div>
  `;

    // HTML 문자열을 innerHTML로 설정
    sideMainContent.innerHTML = sideHtml;
}

function yesButton(){
    const Btns=document.querySelectorAll('.yesno-button');
    Btns[0].style.backgroundColor='green';
    Btns[0].style.color='white';
    Btns[1].style.backgroundColor='#f0f0f0';
    Btns[1].style.color='black';
}

function noButton(){
    const Btns=document.querySelectorAll('.yesno-button');
    Btns[1].style.backgroundColor='red';
    Btns[1].style.color='white';
    Btns[0].style.backgroundColor='#f0f0f0';
    Btns[0].style.color='black';
}



function calculateTotal() {
    const sellInputPoint = document.getElementById('sellInputPoint');
    const selTotalPoint = document.getElementById('selTotalPoint');
}

function userActivityComment(value) {
    const userActivityContent = document.querySelector('.user_active-content');
    let loginCheck='';

    const buttons = document.querySelectorAll('.user-activity-button');
    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');

    if(login===true){
        loginCheck=`<div class="comment_input-content">
                    <input type="text" placeholder="  Add a comment">
                    <button>입력</button>
                </div>`;
    }
    userActivityContent.innerHTML =
        loginCheck+ `
                <div class="comment_safe-content">
                    <p>외부 링크를 조심하세요, 피싱 공격일 수 있습니다.</p>
                </div>
                <div class="comment_sort-content">
                    <ul>
                        <li>Sort by Newest</li>
<!--                        <li> <select id ="comment_sort-select">-->
<!--                            <option>Newest</option>-->
<!--                            <option>Likes</option>-->
<!--                        </select></li>-->
                    </ul>
                </div>
                <div class="comment-container">
                    <div class="comment-content">
                        <img src="../images/me.jpg" alt="Profile Image">
                        <div class="comment-info">
                            <p class="info-name">Kasc&nbsp<span class="comment_input-time">23m ago</span></p>
                            <p class="info-content">The leading alternative to polymarkets [https://vanso.exchange?ref=7214] shows $23mil liquidity and much more volume on the same bet, Arbitrage opportunity? seems there are clueless whales abound.</p>
                            <div class="info-buttons">
<!--                                <button class='heartImg'><span class="material-symbols-outlined">favorite</span>20</button>-->
                                <button class="comment-recomment-btn">답글</button>
                            </div>
                            <div class="recomment_input-content">
                                <input type="text" placeholder="  Add a comment">
                                <button>입력</button>
                            </div>
                        </div>
                    </div>
                    <div class="recomment-container">
                        <div class="recomment-content">
                            <img src="../images/me.jpg" alt="Profile Image">
                            <div class="comment-info">
                                <p class="info-name">itsjoever41-17&nbsp<span class="comment_input-time">11m ago</span></p>
                                <p class="info-content">Huge round of applause for our resident schizo 41-17™askDomerWhatImean hitting -$11.2K in his P/L and trending even worse! We did it Joe!</p>
                                <div class="info-buttons">
<!--                                    <button class='heartImg'><span class="material-symbols-outlined">favorite</span>45</button>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        `;
}

function userActivityRanking(value){
    const userActivityContent = document.querySelector('.user_active-content');
    const buttons = document.querySelectorAll('.user-activity-button');
    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');


    userActivityContent.innerHTML=`
    <div class="user_ranking-content">
                    <div class ="ranking_yes-content">
                        <ul>
                            <li id ="ranking-holders">
                                Yes holders
                            </li>
                            <li id ="ranking-shares">
                                SHARES
                            </li>
                        </ul>
                    </div>
                    <div class="ranking_no-content">
                        <ul>
                            <li id ="ranking-holders">
                                No holders
                            </li>
                            <li id ="ranking-shares">
                                SHARES
                            </li>
                        </ul>
                    </div>

                </div>



                <div class="user_ranking-content">
                    <div class ="ranking_yes-content">
                        <ul>
                            <li>
                                <img src= "../images/me.jpg">
                                Joe-Biden
                            </li>
                            <li class="ranking_yes-amount">
                                355,270
                            </li>
                        </ul>
                    </div>
                    <div class="ranking_no-content">
                        <ul>
                            <li>
                                <img src= "../images/me.jpg">
                                Joe-Biden
                            </li>
                            <li class="ranking_no-amount">
                                355,270
                            </li>
                        </ul>
                    </div>

                </div>
                <div class="user_ranking-content">
                    <div class ="ranking_yes-content">
                        <ul>
                            <li>
                                <img src= "../images/me.jpg">
                                Joe-Biden
                            </li>
                            <li class="ranking_yes-amount">
                                355,270
                            </li>
                        </ul>
                    </div>
                    <div class="ranking_no-content">
                        <ul>
                            <li>
                                <img src= "../images/me.jpg">
                                Joe-Biden
                            </li>
                            <li class="ranking_no-amount">
                                355,270
                            </li>
                        </ul>
                    </div>

                </div>
                `;
}

function userActivityActive(value) {
    const userActivityContent = document.querySelector('.user_active-content');
    const buttons = document.querySelectorAll('.user-activity-button');

    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');

    $.ajax({
        url: '/active',
        type: 'GET',
        success: function(data) {
            userActivityContent.innerHTML = '';
            data.forEach(activity => {
                const date = new Date(activity.activeTimestamp);
                const formattedDate = date.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });
                let activeYesNoClass = 'active-yesno-span';
                let activeNumClass = 'active-num-span';
                let purchaseOrSale = '구매';

                if (activity.choice === 'No') {
                    activeYesNoClass += ' red-text';
                    activeNumClass += ' red-text';
                } else {
                    activeYesNoClass += ' green-text';
                    activeNumClass += ' green-text';
                }

                if (activity.amount < 0) {
                    purchaseOrSale = '판매';
                }

                userActivityContent.innerHTML += `
                        <div class="user_activity-content">
                            <ul>
                                <li><img src="../images/me.jpg"/><span class="active-name-span">${activity.name}</span>님께서 &nbsp;<span class="${activeYesNoClass}">${activity.choice}</span>&nbsp;를&nbsp; <span class="${activeNumClass}">${Math.abs(activity.amount)}</span> 만큼 &nbsp;<span>${purchaseOrSale}</span> 하였습니다</li>
                                <li>${formattedDate}</li>
                            </ul>
                        </div>`;
            });
        }
    });
}


function buyModal(){
    const battingModalContainer = document.querySelector('.batting_modal-container');
    battingModalContainer.style.display='block';
    battingModalContainer.innerHTML=`  <div class="buy_modal-content">
        <p class="modal-title">제목</p>
        <p><span>yes</span>를 선택하셨습니다.</p>
        <p><span>310p</span>를 소모해여 <span>구매</span>하시겠습니까?</p>
        <div class="modal-btns">
            <button class="modal-btns-buy">구매</button>
            <button class="modal-btns-cancel" onclick="battingModalCancle()">취소</button>
        </div>
    </div>`;
}
function sellModal(){
    const battingModalContainer = document.querySelector('.batting_modal-container');
    battingModalContainer.style.display='block';
    battingModalContainer.innerHTML=`<div class="sell_modal-content">
             <p class="modal-title">제목</p>
        <p><span>no</span>를 선택하셨습니다.</p>
        <p>310p를 <span>판매</span>하시겠습니까?</p>
        <div class="modal-btns">
            <button class="modal-btns-sell">판매</button>
            <button class="modal-btns-cancel" onclick="battingModalCancle()">취소</button>
        </div>
        </div>`;
}
function loginModal(){

}
function battingModalCancle(){
    const battingModal = document.querySelector('.batting_modal-container');
    battingModal.style.display='none';
}


function graphChart() {
    new Chart(document.getElementById("line-chart"), {
        type: 'line',
        data: {
            labels: [1500,1600,1700,1750,1800,1850,1900,1950,1999,2050,2100],
            datasets: [
                {
                    data: [168,170,178,190,203,276,408,547,675,734],
                    label: "YES",
                    borderColor: "#3cba9f",
                    fill: false
                },
                {
                    data: [6,3,2,2,7,26,82,172,312,433],
                    label: "NO",
                    borderColor: "#c45850",
                    fill: false
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'World population per region (in millions)'
            },
            scales: {
                x: {
                    grid: {
                        display: false,
                        
                    }
                }
            },
            legend: {
                display: false
            }
        }
    });
}
