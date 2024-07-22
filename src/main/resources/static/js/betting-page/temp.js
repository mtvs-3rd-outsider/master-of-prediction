function userActivityRanking(value) {
    const userActivityContent = document.querySelector('.user_active-content');
    const buttons = document.querySelectorAll('.user_activity-button');

    buttons.forEach((button) => {
        button.classList.remove('active');
    });

    buttons[value].classList.add('active');

    $.ajax({
        url: '/ranking',
        type: 'GET',
        success: function (data) {
            userActivityContent.innerHTML = `<div class="user_ranking-content">
                        <div class="ranking_yes-content">
                            <ul>
                                <li id="ranking-holders">Yes holders</li>
                                <li id="ranking-shares">SHARES</li>
                            </ul>`;
            let noHtml = ` <div class="ranking_no-content">
                            <ul>
                                <li id="ranking-holders">No holders</li>
                                <li id="ranking-shares">SHARES</li>
                            </ul>`;

            data.forEach(ranking => {
                if (ranking.choice === 'yes') {
                    userActivityContent.innerHTML += ` <ul>
                                <li>
                                    <img src="../images/me.jpg">
                                    ${ranking.name}
                                </li>
                                <li class="ranking_yes-amount">${ranking.sum}</li>
                            </ul>`;
                }
                if (ranking.choice === 'no') {
                    noHtml += ` <ul>
                                <li>
                                    <img src="../images/me.jpg">
                                    ${ranking.name}
                                </li>
                                <li class="ranking_no-amount">${ranking.sum}</li>
                            </ul>`;
                }
            });
            userActivityContent.innerHTML += noHtml;
            userActivityContent.innerHTML += `</div>`;

        }
    });
}