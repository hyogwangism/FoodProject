$(document).ready(function(){
	searchFoodList();
	});


function sendAjaxRequest(food_type, page) {
  var selectedValues1 = [];
  var selectedValues2 = [];
  $('.search_div1 input[type=checkbox]:checked').each(function () {
    selectedValues1.push($(this).val());
  });

  $('.search_div2 input[type=checkbox]:checked').each(function () {
    selectedValues2.push($(this).val());
  });

  var search_field = $('#search_field').val();
  var search_type = $('#search_type').val();

  $.ajax({
    type: 'POST',
    url: '/food_list2',
    contentType: 'application/json',
    data: JSON.stringify({
      "food_type": food_type,
      "list1": selectedValues1,
      "list2": selectedValues2,
      "search_field": search_field,
      "search_type": search_type,
      "page": page
    }),
    dataType: 'json',
    success: function (res) {
      var flist = res.flist;
      var totalPages = res.totalPages;

      document.querySelector('.foodList').innerHTML = "";
      for (let i = 0; i < flist.length; i++) {
        const html = `
          <div class="overview">
            <img src="/upload${flist[i].PHOTO_URL}" onclick="location.href='food_cont?NAVI_NO=${flist[i].NAVI_NO}'"/><br>
            <p>
              <span><b>찜</b></span>&nbsp;&nbsp;&nbsp;${flist[i].LIKE_CNT}개<br>
              <span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${flist[i].RESTAURANT_NAME}<br>
              <span><b>음식명</b></span>&nbsp;&nbsp;&nbsp;${flist[i].FOOD_NAME}<br>
              <span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${flist[i].REGION_NAME}<br>
            </p>
          </div>
        `;
        $('.foodList').append(html);
      }

      generatePageLinks(page, totalPages);
    }
  });
}

$(document).on('click', '.page-link', function() {
  const page = $(this).data('page');
  const food_type = $('.tabmenu.green').attr('value');
  sendAjaxRequest(food_type, page);
});

alert(page)

function generatePageLinks(currentPage, totalPages) {
  const maxPageLinks = 10; // 한 번에 표시할 최대 페이지 링크 개수
  const blockSize = 10; // 한 블록의 페이지 개수
  let startPage;
  let endPage;

  if (currentPage <= maxPageLinks) {
    startPage = 1;
    endPage = Math.min(totalPages, maxPageLinks);
  } else {
    const currentBlock = Math.ceil(currentPage / blockSize);
    startPage = (currentBlock - 1) * blockSize + 1;
    endPage = Math.min(startPage + blockSize - 1, totalPages);
  }

  // 페이지 링크 생성 및 추가
  $('.page').empty();

  if (currentPage > 1) {
    const prevPageHtml = `<span class="page-link" data-page="${currentPage - 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;">이전</span>`;
    $('.page').append(prevPageHtml);
  }

  for (let i = startPage; i <= endPage; i++) {
    let pageHtml;
    if (i === currentPage) {
      pageHtml = `<b><span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer;">[${i}]</span></b>`;
    } else {
      pageHtml = `<span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer;">[${i}]</span>`;
    }
    $('.page').append(pageHtml);
  }

  if (currentPage < totalPages) {
    const nextPageHtml = `<span class="page-link" data-page="${currentPage + 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;">다음</span>`;
    $('.page').append(nextPageHtml);
  }
}

function searchFoodList() {
  $('.tabmenu').click(function (e) {
    var food_type = $(this).attr('value');
    $('.tabmenu').css('background-color', 'white');
    $(this).css('background-color', 'green');
    sendAjaxRequest(food_type);
  });

  $('.search_div1 input[type=checkbox], .search_div2 input[type=checkbox]').on('change', function () {
    var food_type = $('.tabmenu.green').attr('value');
    sendAjaxRequest(food_type);
  });

  $('#searchBtn').click(function (e) {
    e.preventDefault();
    var food_type = $('.tabmenu.green').attr('value');
    sendAjaxRequest(food_type);
  });

  $('#search_field').on('input', function () {
    var food_type = $('.tabmenu.green').attr('value');
    sendAjaxRequest(food_type);
  });

  function sendAjaxRequest(food_type) {
    var selectedValues1 = [];
    var selectedValues2 = [];
    $('.search_div1 input[type=checkbox]:checked').each(function () {
      selectedValues1.push($(this).val());
    });

    $('.search_div2 input[type=checkbox]:checked').each(function () {
      selectedValues2.push($(this).val());
    });

    var search_field = $('#search_field').val();
    var search_type = $('#search_type').val();
    var page = 1;

    $.ajax({
      type: 'POST',
      url: '/food_list2',
      contentType: 'application/json',
      data: JSON.stringify({
        "food_type": food_type,
        "list1": selectedValues1,
        "list2": selectedValues2,
        "search_field": search_field,
        "search_type": search_type,
        "page": page
      }),
      dataType: 'json',
      success: function (res) {
        var flist = res.flist;
        var totalPages = res.totalPages;

        document.querySelector('.foodList').innerHTML = "";
        for (let i = 0; i < flist.length; i++) {
          const html = `
            <div class="overview">
              <img src="/upload${flist[i].PHOTO_URL}" onclick="location.href='food_cont?NAVI_NO=${flist[i].NAVI_NO}'"/><br>
              <p>
                 <span><b>찜</b></span>&nbsp;&nbsp;&nbsp;${flist[i].LIKE_CNT}개<br>
                <span><b>식당명</b></span>&nbsp;&nbsp;&nbsp;${flist[i].RESTAURANT_NAME}<br>
                <span><b>음식명</b></span>&nbsp;&nbsp;&nbsp;${flist[i].FOOD_NAME}<br>
                <span><b>지역</b></span>&nbsp;&nbsp;&nbsp;${flist[i].REGION_NAME}<br>
              </p>
            </div>
          `;
          $('.foodList').append(html);
        }

        generatePageLinks(page, totalPages);
      }
    });
  }

  function generatePageLinks(currentPage, totalPages) {
    const maxPageLinks = 10; // 한 번에 표시할 최대 페이지 링크 개수
    const blockSize = 10; // 한 블록의 페이지 개수
    let startPage;
    let endPage;

    if (currentPage <= maxPageLinks) {
      startPage = 1;
      endPage = Math.min(totalPages, maxPageLinks);
    } else {
      const currentBlock = Math.ceil(currentPage / blockSize);
      startPage = (currentBlock - 1) * blockSize + 1;
      endPage = Math.min(startPage + blockSize - 1, totalPages);
    }

    // 페이지 링크 생성 및 추가
    $('.page').empty();

    if (currentPage > 1) {
      const prevPageHtml = `<span class="page-link" data-page="${currentPage - 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;">이전</span>`;
      $('.page').append(prevPageHtml);
    }

    for (let i = startPage; i <= endPage; i++) {
      let pageHtml;
      if (i === currentPage) {
        pageHtml = `<b><span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer;">[${i}]</span></b>`;
      } else {
        pageHtml = `<span class="page-link" data-page="${i}" style="display: inline-block; margin: 0 5px; cursor: pointer;">[${i}]</span>`;
      }
      $('.page').append(pageHtml);
    }

    if (currentPage < totalPages) {
      const nextPageHtml = `<span class="page-link" data-page="${currentPage + 1}" style="display: inline-block; margin: 0 5px; cursor: pointer;">다음</span>`;
      $('.page').append(nextPageHtml);
    }
  }
}