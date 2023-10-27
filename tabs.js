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

function addTab(tabName) {
    // 새로운 탭 생성
    var tabsContainer = document.getElementById('tabs');
    var newTabId = 'tab' + (tabsContainer.childElementCount + 1); // 고유한 ID 생성
    var newTab = document.createElement('li');
    newTab.className = 'tab';
    newTab.id = newTabId;
    newTab.innerHTML = tabName;
    tabsContainer.appendChild(newTab);

    // 새로운 탭의 내용 생성
    var tabContents = document.getElementById('tabContents');
    var newTabContent = document.createElement('div');
    newTabContent.id = newTabId;
    newTabContent.className = 'tab-content';
    newTabContent.innerHTML = `
        <h2>${tabName}</h2>
        <p>${tabName}의 내용이 여기에 들어갑니다.</p>
    `;
    tabContents.appendChild(newTabContent);

    // 탭 클릭 이벤트 핸들러 등록
    newTab.addEventListener('click', function() {
        var activeTab = document.querySelector('.tab.active');
        if (activeTab) {
            activeTab.classList.remove('active');
        }
        newTab.classList.add('active');
        showTab(newTabId);
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
    }
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

addTab('대시보드'); // 초기 탭 생성


function changeSidebar(menu) {
    event.preventDefault(); // 기본 동작을 중단

    var sidebar = document.getElementById('sidebar');
    var sidebarContent = '';

    if (menu === 'menu1') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('Tab1')">탭 1</a></li>
                        <li><a href="#" onclick="addTab('Tab2')">탭 2</a></li>
                        <li><a href="#" onclick="addTab('Tab3')">탭 3</a></li>
                    </ul>
                `;
    } else if (menu === 'menu2') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('Tab4')">탭 4</a></li>
                        <li><a href="#" onclick="addTab('Tab5')">탭 5</a></li>
                        <li><a href="#" onclick="addTab('Tab6')">탭 6</a></li>
                    </ul>
                `;
    } else if (menu === 'menu3') {
        sidebarContent = `
                    <ul class="sidebar-links">
                        <li><a href="#" onclick="addTab('Tab7')">탭 7</a></li>
                        <li><a href="#" onclick="addTab('Tab8')">탭 8</a></li>
                        <li><a href="#" onclick="addTab('Tab9')">탭 9</a></li>
                    </ul>
                `;
    }

    sidebar.innerHTML = sidebarContent;
}