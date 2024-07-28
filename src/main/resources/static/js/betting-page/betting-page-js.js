let login = true;
var sideSelect = true;
var activeSelect = 0;
let selectedChoice = null;
var sumYPoint;
var sumNPoint
let chartInstance = null;
var copyGraphDataList;
var subjectNo= document.getElementById("subjectNo").value;
let bettingState = document.getElementById("betting-state").textContent;
const width = window.innerWidth;
const height = window.innerHeight;


// 페이지 로드 시와 창 크기 변경 시 업데이트 함수 호출
const initBettingPage = function () {
    if(document.getElementById("loggedInUserId").value==0){
        login=false;
    }else{
        login=true;
    }


    sideSelectBuy();
    graphSettingButton(5);
    sumYPoint = document.getElementById("sumYPoint").value;
    sumNPoint=document.getElementById("sumNPoint").value;

    // batting_modal-container 숨기기
    const bettingModalContainer = document.querySelector('.batting_modal-container');
    bettingModalContainer.style.display = 'none';

    // popup_setting-content 숨기기
    const popupSettingContent = document.querySelector('.popup_setting-content');
    popupSettingContent.style.display = 'none';

    // const resultModal = document.getElementById("myModal");
    // resultModal.style.display = 'flex';
    //반응형

    responsive();
}

function responsive() {
    const htmlContainer = document.getElementById("top-div");
    const currentWidth = htmlContainer.offsetWidth; // top-div의 너비 가져오기
    const graphSettingContentButton = document.querySelectorAll('.graph_setting-content-button');

    if (currentWidth <= 760) {
        graphSettingContentButton[1].style.display='none';
        graphSettingContentButton[4].style.display='none';
        document.querySelector(".html-container").style.width = currentWidth + "px"; // top-div의 너비에 맞춰 설정
        document.querySelector('.side-container').style.display='none';
        document.querySelector('.mobile-have-point').style.display='block';
        if (login===true){
            document.getElementById('havePoint').innerText=document.getElementById('userPoint').value+"Point";
        }
        document.querySelector('.title_desktop').style.display='none';
        document.querySelectorAll('.title_mobile')[0].style.display='flex';
        document.querySelectorAll('.title_mobile')[1].style.display='flex';

    } else {
        document.querySelector(".buy-mobile-content").style.display='none';
        document.querySelector(".html-container").style.width = ""; // 760px 초과 시 기본값으로 되돌리기
    }
}

window.addEventListener('load', initBettingPage);

document.addEventListener('DOMContentLoaded', function() {
    const bettingWarningElement = document.querySelector('.betting-warning');

    // Assuming you have the userAuthority value from the controller
    const userAuthority = '{{ userAuthority }}';

    if (userAuthority === 'ROLE_ADMIN') {
        bettingWarningElement.classList.add('hidden');
    } else {
        bettingWarningElement.classList.remove('hidden');
    }
});
function sideSelectBuy() {
    sideSelect = true;
    const buyButton = document.querySelector('.buy-item button');
    const sellButton = document.querySelector('.sell-item button');
    buyButton.style.color='#8D7CBB';
    buyButton.style.borderBottom='3px solid #8D7CBB';
    sellButton.style.borderBottom='none';
    sellButton.style.color='gray';
    // 123
    sideSelect = true;
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

function graphSettingButton(value) {
    const buttons = document.querySelectorAll('.graph_setting-content-button');
    const loggedSubjectNo = document.getElementById('loggedSubjectNo').value;
    buttons.forEach(button => {
        button.style.backgroundColor = 'white';
        button.style.setProperty('--md-sys-color-primary', '#6750a4');
    });

    const graphTime = buttons[value].textContent.trim();
    const GraphDTO = {
        graphTime: graphTime,
        subjectNo: loggedSubjectNo
    };

    fetch(`/graph/${subjectNo}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(GraphDTO)
    })
        .then(response => response.json())
        .then(graphDTOList => {
            // graphDTOList 데이터 처리
            graphChart(graphDTOList);
        })
        .catch(error => {
            console.error('Error:', error);
        });

    buttons[value].style.backgroundColor = '#F0C8D0';
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
function changeSetting(){
    const yesSwich=document.getElementById("yes-Switch");
    const noSwich =document.getElementById("no-Switch");
    if (yesSwich.checked===true){
        yesSwich.checked=false;
    }else{
        yesSwich.checked=true;
    }
    if (noSwich.checked===true){
        noSwich.checked=false;
    }else{
        noSwich.checked=true;
    }
    updateChart();
}
function popupCancle() {
    const popupSetting = document.querySelector('.popup_setting-content');
    popupSetting.style.display = 'none';
}

function createSideMain() {
    const sideMainContent = document.querySelector('.side_main-container');
    let battingModalStr;
    if (login === true) {
        if (sideSelect === true) {
            if (bettingState==="진행중"){
                battingModalStr = `<button class="batting-buy-modal" onclick="buyModal()" >구매</button>`;
            }else {
                battingModalStr = `<button class="batting-buy-modal">종료된 상품</button>`;
            }
        } else {
            if (bettingState==="진행중"){
                battingModalStr = `<button class="batting-sell-modal" onclick="sellModal()">판매</button>`;
            }else{
                battingModalStr = `<button class="batting-sell-modal"">종료된 상품</button>`;
            }
        }
    } else {
        if (sideSelect === true) {
            battingModalStr = `<button class="batting-buy-modal">로그인 하세요</button>`;
        } else {
            battingModalStr = `<button class="batting-sell-modal">로그인 하세요</button>`;
        }
    }
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
            <li>${sideSelect ? '구매포인트' : '판매포인트'}</li>
            <li>
                <div class="side-point-input">
                    <button onclick="changePoint(10)">+</button>
                    <input type="number" id="inputPoint" onchange="calculateTotal()" step="10" min="0" onKeyUp="fnKeyupNum(this)">
                    <button onclick="changePoint(-10)">-</button>
                </div>
            </li>
            <li>
                ${battingModalStr}
            </li>
            <li>
                <div class="side-sum-content">
                    합계 <span id="totalPoint">0<span>Point</span></span>
                </div>
            </li>
            <li>
                <div class="side-return-content">
                    보유상품<span id="YesOrNo"></span> <span id="returnPoint"><span>Point</span></span>
                </div>
            </li>
            <li class="mobile-have-point" style="display: none"> <div class="side-have-content"> 보유포인트<span id="havePoint"><span>Point</span></span></div></li>
        </ul>
    </div>
    `;
    sideMainContent.innerHTML = sideHtml;
    document.getElementById('inputPoint').disabled = true;
    document.querySelector(sideSelect ? '.batting-buy-modal' : '.batting-sell-modal').disabled = true;
    const htmlContainer = document.getElementById("top-div");
    const currentWidth = htmlContainer.offsetWidth; // top-div의 너비 가져오기

    if (currentWidth <= 760) {
        document.querySelector('.mobile-have-point').style.display='block';
    }else{
        document.querySelector('.mobile-have-point').style.display='none';
    }
}

function yesButton(){
    document.getElementById('YesOrNo').innerText="Yes";
    selectedChoice = 'YES';
    enableInput();
    updateReturnPoint(sumYPoint);
    const Btns=document.querySelectorAll('.yesno-button');
    Btns[0].style.backgroundColor='green';
    Btns[0].style.color='white';
    Btns[1].style.backgroundColor='#f0f0f0';
    Btns[1].style.color='black';
}

function noButton(){
    document.getElementById("YesOrNo").innerText="No";
    selectedChoice = 'NO';
    enableInput();
    updateReturnPoint(sumNPoint);
    const Btns=document.querySelectorAll('.yesno-button');
    Btns[1].style.backgroundColor='red';
    Btns[1].style.color='white';
    Btns[0].style.backgroundColor='#f0f0f0';
    Btns[0].style.color='black';
}
function updateReturnPoint(point) {
    document.getElementById("returnPoint").innerText = point + " Point";
}
function enableInput() {
    document.getElementById('inputPoint').disabled = false;
    document.querySelector(sideSelect ? '.batting-buy-modal' : '.batting-sell-modal').disabled = false;
    validatePurchaseButton();
}

function changePoint(amount) {
    const input = document.getElementById('inputPoint');
    let value = parseInt(input.value || '0') + amount;
    if (value < 0) value = 0;
    input.value = value;
    calculateTotal();
}

function calculateTotal() {
    const inputPoint = document.getElementById('inputPoint');
    const totalPoint = document.getElementById('totalPoint');
    totalPoint.textContent = `${inputPoint.value} Point`;
    validatePurchaseButton();
}

function validatePurchaseButton() {
    const inputPoint = document.getElementById('inputPoint');
    const purchaseButton = document.querySelector(sideSelect ? '.batting-buy-modal' : '.batting-sell-modal');
    if (selectedChoice && inputPoint.value > 0) {
        purchaseButton.disabled = false;
    } else {
        purchaseButton.disabled = true;
    }
}


function addComment(value) {
    const newCommentInput = document.getElementById('new-comment');
    const loggedInUserId =document.getElementById('loggedInUserId').value;
    const newCommentContent = newCommentInput.value;
    const loggedSubjectNo = document.getElementById('loggedSubjectNo').value;

    if (newCommentContent.trim() === '') {
        alert('댓글을 입력하세요.');
        return;
    }

    const TblCommentDTO = {
        commentContent: newCommentContent,
        commentUserNo: loggedInUserId,
        commentSubjectNo: loggedSubjectNo // 동적으로 주제 ID를 할당합니다.
    };

    fetch(`/comment/${subjectNo}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',

        },
        credentials: 'include',
        body: JSON.stringify(TblCommentDTO)
    })
        .then(response => {
            if (response.ok) {
                newCommentInput.value = ''; // 입력 필드 비우기
                userActivityComment(value); // 댓글 목록 새로고침
            } else {
                console.error('댓글 등록 실패');
            }
        })
        .catch(error => {
            console.error('댓글 등록 중 오류 발생:', error);
        });
}
function userActivityComment(value) {
    const userActivityContent = document.querySelector('.user_active-content');
    let loginCheck = '';
    let replyCheck='';
    const buttons = document.querySelectorAll('.user-activity-button');
    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');

    if (login === true) {
        loginCheck = `<div class="comment_input-content">
                    <input type="text" id="new-comment" placeholder="  Add a comment">
                    <button onclick="addComment(${value})">입력</button>
                </div>`;
    }else{
        loginCheck = '';
        replyCheck=`<button class="comment-recomment-btn">답글</button>`;
    }

    $.ajax({
        url: `/comment/${subjectNo}`,
        type: 'GET',
        success: function(data) {
            userActivityContent.innerHTML = loginCheck + `
                    <div class="comment_safe-content">
                        <p>외부 링크를 조심하세요, 피싱 공격일 수 있습니다.</p>
                    </div>
                    <div class="comment_sort-content">
                        <ul>
                            <li>Sort by Newest</li>
                        </ul>
                    </div>
                    <div class="comment-container" id="comment-container">
                    </div>`;

            const commentContainer = document.getElementById('comment-container');
            let commentsList = [];

            data.forEach(comment => {
                const date = new Date(comment.commentTimestamp);
                const formattedDate = date.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });

                let commentObj = commentsList.find(c => c.commentNo === comment.commnetNo);

                if (!commentObj) {
                    commentObj = {
                        commentNo: comment.commnetNo,
                        commentContent: comment.commentContent,
                        commentTimestamp: formattedDate,
                        commentUserName: comment.commentUserName,
                        imgUrl: comment.imgUrl,
                        userNo: comment.userNo,
                        replies: []
                    };
                    commentsList.push(commentObj);
                }

                if (comment.replyNo) {
                    const replyDate = new Date(comment.replyTimestamp);
                    const formattedReplyDate = replyDate.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });

                    commentObj.replies.push({
                        replyContent: comment.replyContent,
                        replyTimestamp: formattedReplyDate,
                        replyUserName: comment.replyUserName,
                        replyImgUrl: comment.replyImgUrl,
                        replyUserNo:comment.replyUserNo
                    });
                }
            });
            // 댓글을 commentTimestamp 기준으로 내림차순 정렬
            commentsList.sort((a, b) => new Date(b.commentTimestamp) - new Date(a.commentTimestamp));

            // 정렬된 댓글을 HTML로 생성
            commentsList.forEach(comment => {
                if (login===true){
                    replyCheck=` <button class="comment-recomment-btn" onclick="showReplyInput(${comment.commentNo})">답글</button>`;
                }
                let commentHtml = `
                        <div class="comment-content">
                            <a href="/mypage/${comment.userNo}">
                                <img src="${comment.imgUrl}" alt="Profile Image" id="comment-profile">
                            </a>
                            <div class="comment-info">
                                <p class="info-name">${comment.commentUserName}&nbsp;<span class="comment_input-time">${comment.commentTimestamp}</span></p>
                                <p class="info-content">${comment.commentContent}</p>
                                <div class="info-buttons">
                                   `+replyCheck+`
                                </div>
                                <div class="recomment_input-content" id="reply-input-${comment.commentNo}" style="display:none;">
                                    <input type="text" placeholder="  Add a comment">
                                    <button onclick="addReply(${comment.commentNo})">입력</button>
                                </div>
                            </div>
                        </div>`;

                if (comment.replies.length > 0) {
                    commentHtml += '<div class="recomment-container">';
                    comment.replies.forEach(reply => {
                        commentHtml += `
                                <div class="recomment-content">
                                <a href="/mypage/${reply.replyUserNo}">
                                    <img src="${reply.replyImgUrl}" alt="Profile Image">
                                    </a>
                                    <div class="comment-info">
                                        <p class="info-name">${reply.replyUserName}&nbsp;<span class="comment_input-time">${reply.replyTimestamp}</span></p>
                                        <p class="info-content">${reply.replyContent}</p>
                                        <div class="info-buttons"></div>
                                    </div>
                                </div>`;
                    });
                    commentHtml += '</div>';
                }

                commentContainer.innerHTML += commentHtml;
            });
        },
        error: function(xhr, status, error) {
            console.error(`Error: ${error}`);
        }
    });
}

function showReplyInput(commentNo) {
    const replyInput = document.getElementById(`reply-input-${commentNo}`);
    if (replyInput.style.display === 'none') {
        replyInput.style.display = 'block';
    } else {
        replyInput.style.display = 'none';
    }
}


function addReply(commentNo) {
    const replyInput = document.getElementById(`reply-input-${commentNo}`).getElementsByTagName('input')[0];
    const newReply = replyInput.value;
    const loggedInUserId = document.getElementById('loggedInUserId').value;

    if (newReply.trim() === '') {
        alert('답글을 입력하세요.');
        return;
    }

    const TblReplyDTO = {
        replyContent: newReply,
        replyUserNo: loggedInUserId,
        replyCommentNo: commentNo // 연결된 댓글 ID
    };

    fetch(`/comment/${commentNo}/reply/${subjectNo}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(TblReplyDTO)
    })
        .then(response => {
            if (response.ok) {
                replyInput.value = ''; // 입력 필드 비우기
                userActivityComment(0); // 댓글 목록 새로고침
            } else {
                console.error('답글 등록 실패');
            }
        })
        .catch(error => {
            console.error('답글 등록 중 오류 발생:', error);
        });
}

// function commentProfile(userNo) {
//     // 서버로 보낼 URL 구성
//     const url = `commentProfile/${userNo}`;
//
//     // Fetch API를 사용하여 POST 요청 전송
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({ userNo: userNo })
//     })
//         .then(response => response.json())
//         .then(data => {
//             // 서버 응답 처리
//             console.log(data);
//         })
//         .catch(error => {
//             // 에러 처리
//             console.error(error);
//         });
// }

function userActivityRanking(value) {
    const userActivityContent = document.querySelector('.user_active-content');
    const buttons = document.querySelectorAll('.user-activity-button');
    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');
    $.ajax({
        url: `/ranking/${subjectNo}`,
        type: 'GET',
        success: function (data) {
            // HTML 생성 함수
            const generateRankingHtml = (choice, className) => {
                let html = `
          <ul>
            <li id="${className}-holders">${choice} holders</li>
            <li id="${className}-shares">SHARES</li>
          </ul>
        `;

                // 데이터를 sum 값 내림차순으로 정렬
                data.filter(ranking => ranking.choice === choice.toLowerCase())
                    .sort((a, b) => b.sum - a.sum)
                    .forEach(ranking => {
                        html += `
              <ul>
                <li>
                <a href="/mypage/${ranking.userNo}">
                  <img src="${ranking.imgUrl}">
                  </a>
                  ${ranking.name}
                </li>
                <li class="${className}-amount">${ranking.sum}</li>
              </ul>
            `;
                    });

                return html;
            };

            // HTML 생성 및 삽입
            userActivityContent.innerHTML = `
        <div class="user_ranking-content">
          <div class="ranking_yes-content">${generateRankingHtml('Yes', 'ranking_yes')}</div>
          <div class="ranking_no-content">${generateRankingHtml('No', 'ranking_no')}</div>
        </div>
      `;
        }
    });
}


function userActivityActive(value){
    const userActivityContent = document.querySelector('.user_active-content');

    const buttons = document.querySelectorAll('.user-activity-button');

    buttons.forEach((button, index) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');

    $.ajax({
        url: `/active/${subjectNo}`,
        type: 'GET',
        success: function (data) {
            userActivityContent.innerHTML = '';
            data.forEach(activity => {
                const date = new Date(activity.activeTimestamp);
                const formattedDate = date.toLocaleString('ko-KR', {timeZone: 'Asia/Seoul'});
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
                const htmlContainer = document.getElementById("top-div");
                const currentWidth = htmlContainer.offsetWidth; // top-div의 너비 가져오기
                let activeStr;
                if (currentWidth <= 760) {
                    activeStr = `<li><a href="/mypage/${activity.userNo}"><img src="${activity.imgUrl}"/></a><span class="active-name-span">${activity.name}</span>&nbsp;<span class="${activeYesNoClass}">${activity.choice}</span>&nbsp;&nbsp; <span class="${activeNumClass}">${Math.abs(activity.amount)}</span> &nbsp;<span>${purchaseOrSale}</span></li>`;
                } else {
                    activeStr=`<li><a href="/mypage/${activity.userNo}"><img src="${activity.imgUrl}"/></a><span class="active-name-span">${activity.name}</span>님께서 &nbsp;<span class="${activeYesNoClass}">${activity.choice}</span>&nbsp;를&nbsp; <span class="${activeNumClass}">${Math.abs(activity.amount)}</span> 만큼 &nbsp;<span>${purchaseOrSale}</span> 하였습니다</li>
                                <li>${formattedDate}</li>`;
                }
                userActivityContent.innerHTML += `
                        <div class="user_activity-content">
                            <ul>
                                `+activeStr+`
                            </ul>
                        </div>`;
            });
        }
    });
}

function buyModal() {
    const totalPoints = document.getElementById('totalPoint').innerText;
    const battingModalContainer = document.querySelector('.batting_modal-container');
    battingModalContainer.style.display = 'flex';
    battingModalContainer.innerHTML = `
        <div class="buy_modal-content">
            <p class="modal-title">제목</p>
            <p><span>${selectedChoice}</span>를 선택하셨습니다.</p>
            <p><span>${totalPoints}</span>를 소모하여 <span>구매</span>하시겠습니까?</p>
            <div class="modal-btns">
                <button class="modal-btns-buy" onclick="submitPurchase()">구매</button>
                <button class="modal-btns-cancel" onclick="battingModalCancel()">취소</button>
            </div>
        </div>`;
}


function battingModalCancel() {
    const battingModalContainer = document.querySelector('.batting_modal-container');
    battingModalContainer.style.display = 'none';
}

function submitPurchase() {
    const orderAmount = parseInt(document.getElementById('inputPoint').value);
    const orderChoice = selectedChoice;

    $.ajax({
        url: `/buyItem/${subjectNo}`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ orderAmount, orderChoice }),
        success: function(response) {
            alert(response.message);
            // window.location.href = '/betting/' + urlParams.get('subjectNo');
            location.reload(true);

        },
        error: function(xhr) {
            const response = JSON.parse(xhr.responseText);
            alert(response.message);
            if (response.redirectUrl) {
                window.location.href = response.redirectUrl;
            }
        }
    });
}
function submitSale() {
    const orderAmount = parseInt(document.getElementById('inputPoint').value);
    const orderChoice = selectedChoice;

    $.ajax({
        url: `/sellItem/${subjectNo}`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ orderAmount, orderChoice }),
        success: function(response) {
            alert(response.message);
            window.location.href = `/betting/${subjectNo}`;
        },
        error: function(xhr) {
            const response = JSON.parse(xhr.responseText);
            alert(response.message);
            if (response.redirectUrl) {
                window.location.href = response.redirectUrl;
            }
        }
    });
}

function sellModal() {
    const totalPoints = document.getElementById('totalPoint').innerText;
    const battingModalContainer = document.querySelector('.batting_modal-container');
    battingModalContainer.style.display = 'flex';
    battingModalContainer.innerHTML = `
        <div class="sell_modal-content">
            <p class="modal-title">제목</p>
            <p><span>${selectedChoice}</span>를 선택하셨습니다.</p>
            <p><span>${totalPoints}</span>를 <span>판매</span>하시겠습니까?</p>
            <div class="modal-btns">
                <button class="modal-btns-sell" onclick="submitSale()">판매</button>
                <button class="modal-btns-cancel" onclick="battingModalCancel()">취소</button>
            </div>
        </div>`;
}
function loginModal(){

}
function battingModalCancle(){
    const battingModal = document.querySelector('.batting_modal-container');
    battingModal.style.display='none';
}


function updateChart() {
    graphChart(copyGraphDataList);
}

function graphChart(graphDTOList) {
    if (chartInstance) {
        chartInstance.destroy();
    }
    copyGraphDataList=graphDTOList;
    const labels = graphDTOList.map(item => {
        const [date, time] = item.displayTime.split(' ');
        const [hour, minute] = time.split(':');
        return `${date.slice(5)}\n${hour}:${minute}`;
    }).reverse();

    const yesData = graphDTOList.map(item => item.yesRate).reverse();
    const noData = graphDTOList.map(item => item.noRate).reverse();

    const canvas = document.getElementById("line-chart");
    const ctx = canvas.getContext("2d");

    // Set the canvas width to 100% before creating the chart instance
    canvas.style.width = '100%';
    canvas.width = canvas.offsetWidth; // Ensure the canvas width is set properly

    // Get the state of the checkboxes
    const yesSwitchChecked = document.getElementById("yes-Switch").checked;
    const noSwitchChecked = document.getElementById("no-Switch").checked;

    // Construct datasets based on checkbox states
    const datasets = [];
    if (yesSwitchChecked) {
        datasets.push({
            data: yesData,
            label: "YES",
            borderColor: "#3cba9f",
            fill: false,
            hidden: !yesSwitchChecked // Hide the dataset if the checkbox is unchecked
        });
    }
    if (noSwitchChecked) {
        datasets.push({
            data: noData,
            label: "NO",
            borderColor: "#c45850",
            fill: false,
            hidden: !noSwitchChecked // Hide the dataset if the checkbox is unchecked
        });
    }

    chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: datasets
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            title: {
                display: true,
                text: 'Betting Order Data'
            },
            scales: {
                y: {
                    min: 0, // y축 최소값 설정
                    max: 100, // y축 최대값 설정
                    ticks: {
                        stepSize: 25 // y축 눈금 간격 설정
                    }
                },
                x: {
                    grid: {
                        display: false
                    }
                }
            },
            legend: {
                display: false
            }
        }
    });
}


// *** 정산하기 모달 구현 부분 ***

var modal = document.getElementById("myModal");
var btn = document.getElementById("openModalBtn");
var span = document.getElementsByClassName("close")[0];
var yesBtn = document.getElementById("yesBtn");
var noBtn = document.getElementById("noBtn");

btn.onclick = function() {
    modal.style.display = "flex";
}
span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
yesBtn.onclick = function() {

    fetch('accountResult', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ result: 'Yes',subNo:subjectNo
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            modal.style.display = "none";
            location.reload();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
noBtn.onclick = function() {
    fetch('accountResult', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ result: 'No',subNo:subjectNo })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            modal.style.display = "none";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// *** 모달 구현 부분 끝 ***


function fnKeyupNum(evt) {
    // 숫자가 아닌 문자인 경우 모두 빈('') 문자로 치환
    evt.value = evt.value.replace(/[^0-9]/g, '');
}

function sideCancle(){
    document.querySelector('.side-container').style.display='none';
}
function sideOpen() {
    const sideContainer = document.querySelector('.side-container');
    const computedStyle = window.getComputedStyle(sideContainer);

    if (computedStyle.display === "none") {
        sideContainer.style.display = 'block';
    } else {
        sideContainer.style.display = 'none';
    }
}




