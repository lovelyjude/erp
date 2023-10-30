function showTab(tabId) {
    // 모든 탭 내용을 숨김
    var tabContents = document.querySelectorAll('.tab-content');
    for (var i = 0; i < tabContents.length; i++) {
        tabContents[i].style.display = 'none';
    }

    // 선택한 탭의 내용을 보임
    var selectedTab = document.getElementById(tabId);
    if (selectedTab) {
        selectedTab.style.display = 'block';
    }
}

function addTab(tabName, pageUrl) {
    // 새로운 탭 생성
    var tabsContainer = document.getElementById('tabs');
    var newTabId = 'tab' + (tabsContainer.childElementCount + 1); // 고유한 ID 생성
    var newTab = document.createElement('li');
    newTab.className = 'tab';
    newTab.id = newTabId;
    newTab.innerHTML = tabName;
    tabsContainer.appendChild(newTab);

    // 탭 클릭 이벤트 핸들러 등록
    newTab.addEventListener('click', function() {
        var activeTab = document.querySelector('.tab.active');
        if (activeTab) {
            activeTab.classList.remove('active');
        }
        newTab.classList.add('active');
        showTab(newTabId);
        loadTab(pageUrl);
    });

    // 닫기 버튼 추가
    var closeButton = document.createElement('span');
    closeButton.className = 'close-button';
    closeButton.innerHTML = '×';
    closeButton.addEventListener('click', function(event) {
        event.stopPropagation(); // 부모 요소의 이벤트 전파 방지
        removeTab(newTabId);
    });
    newTab.appendChild(closeButton);

    // 처음 탭 추가 시, 활성화
    if (tabsContainer.childElementCount === 1) {
        newTab.classList.add('active');
        showTab(newTabId);
        loadTab(pageUrl);
    }
}

function loadTab(pageUrl) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var tabContents = document.getElementById('tabContents');
            tabContents.innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", pageUrl, true);
    xhttp.send();
}


function removeTab(tabId) {
    // 탭 제거
    var tabsContainer = document.getElementById('tabs');
    var tabToRemove = document.getElementById(tabId);
    tabsContainer.removeChild(tabToRemove);

    // 탭 내용 제거
    var tabContents = document.getElementById('tabContents');
    var tabContentToRemove = document.getElementById(tabId);
    tabContents.removeChild(tabContentToRemove);

    // 삭제된 탭 다음 탭을 활성화 (만약 삭제된 탭이 활성화 상태였다면)
    var activeTab = document.querySelector('.tab.active');
    if (!activeTab && tabsContainer.childElementCount > 0) {
        var firstTab = tabsContainer.children[0];
        firstTab.classList.add('active');
        showTab(firstTab.id);
    }
}


function changeSidebar(menu) {
    event.preventDefault(); // 기본 동작을 중단

    var sidebar = document.getElementById('sidebar');
    var sidebarContent = '';

    if (menu === 'menu1') {
        sidebarContent = `
        <ul class="sidebar-links">
            <li><a href="#" onclick="addTab('제품관리', 'html/tab1.html')">제품관리</a></li>
        </ul>
    `;
    } else if (menu === 'menu2') {
        sidebarContent = `
        <ul class="sidebar-links">
            <li><a href="#" onclick="addTab('발주 거래처', 'html/tab2.html')">발주 거래처</a></li>
            <li><a href="#" onclick="addTab('수주 거래처', 'html/tab3.html')">수주 거래처</a></li>
            <li><a href="#" onclick="addTab('미수금', 'html/tab4.html')">미수금</a></li>
        </ul>
    `;
    } else if (menu === 'menu3') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('견적서관리','html/quoteList.html')">견적서관리</a></li>
                        <li><a href="#" onclick="addTab('지출결의서')">지출결의서</a></li>
                    </ul>
                `;
    } else if (menu === 'menu4') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('주문현황')">주문현황</a></li>
                        <li><a href="#" onclick="addTab('발주')">발주</a></li>
                        <li><a href="#" onclick="addTab('수주')">수주</a></li>
                    </ul>
                `;
    } else if (menu === 'menu5') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('고객불만접수')">고객불만접수</a></li>
                        <li><a href="#" onclick="addTab('공지사항','html/notice_list.html')">공지사항</a></li>
                    </ul>
                `;
    }

    sidebar.innerHTML = sidebarContent;
}