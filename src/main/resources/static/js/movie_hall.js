//
//   所属板块：管理影厅
//   功能：修改css
//       使用动画所用的js
//      除去提交form表单所用js
//
//
//
//
// 功能：点击新增影厅按钮，出现新增影厅的弹框


$('#new_hall').on("click", function () {
  $('#float_box').css({ "display": "block" })
  $('#new_hall_form').css({ "display": "block" })
  $('#edit_hall_form').css({ "display": "none" })
})


 // 功能：取消新增影厅弹框


$('#cancel_new_hall').on("click", function () {
  $('#float_box').css({ "display": "none" })
  $('#new_hall_form').css({ "display": "none" })
  $('#edit_hall_form').css({ "display": "none" })
})

$('.all_hall').eq(1).onchange = function(){
  console.log('点击按钮')

// 功能：点击影厅删除按钮，删除影厅

  var a = $('.delete_hall')
  console.log(a)
  a.each(function () {
    $(this).on('click', function () {
      // 删除按钮所在的一行
      var parent = $(this).parent().parent()
      // 影厅id
      var id = parent.children()[0]
      // console.log(parent[0])
      // console.log(id.value)
      // console.log( typeof id.value)

      $.ajax({
        url: '/delHall',
        type: 'get',
        contentType: 'application/json;charset=UTF-8',
        data:{
          id:id.value
        },
        dataType: 'json',
        success: data => {
          if(data.sign == 1){
            parent.remove()
          }

        }
      })
    })
  })


  /*
   * 功能：编辑当前影厅
  */

  $('.edit_hall').each(function () {
    $(this).on("click", function () {
      // $('#edit_hall_form').remove($('#edit_d'))
      var parent = $(this).parent().parent()
      // 影厅id
      var id = parent.children()[0].value
      var hallName = parent.children()[1].innerHTML
      var movieType = parent.children()[2].innerHTML
      var seatLine = parent.children()[3].innerHTML
      var seatColumn = parent.children()[4].innerHTML

      $('#edit_id')[0].value = 111
      $('#edit_hallName')[0].value = hallName
      $('#edit_seatLine')[0].value = seatLine
      $('#edit_seatColumn')[0].value = seatColumn
      // console.log($('#edit_seatColumn')[0])
      console.log($('#edit_id')[0])
      console.log($('#edit_id')[0].value)
      var str =  `<input id="edit_d" hidden value="${id}" ></input>`
      $('#edit_hall_form').append(str)

      $('.select_movieType').each(function(){
        if(( $(this) )[0].value == movieType){
          if( ( $(this) )[0].value == movieType){
            ( $(this) )[0].selected=true;
          }
        }
      })

      $('#float_box').css({ "display": "block" })
      $('#new_hall_form').css({ "display": "none" })
      $('#edit_hall_form').css({ "display": "block" })

      // $('#edit_hall_form').remove($('#edit_d'))
    })
  })

  // 功能：处理提交编辑影厅表单数据
// 未完成
  $('#edit_hall_form').on('submit', e => {
    e.preventDefault()
    const formData = $('#edit_hall_form').serializeArray()
    var o = {}
    $.each(formData, function () {
      if (o[this.name]) {
        if (!o[this.name].push) {
          o[this.name] = [o[this.name]];
        }
        o[this.name].push(this.value || '');
      } else {
        o[this.name] = this.value || '';
      }
    })
    console.log(o)
    console.log(JSON.stringify(o))
    var fd = JSON.stringify(o)

    $.ajax({
      url: '/editHall',
      type: 'post',
      data: fd,
      dataType: 'json',
      contentType: 'application/json;charset=UTF-8',
      catch: false,      //清除上次请求的缓存、
      // async: false,   //同步
      success: data => {
        console.log(data)

        var str = `
          <form action="" class="hall">
            <input hidden value="${data.id}"></input>
            <p class="hall_name hall_p">${data.hallName}</p>
            <p class="hall_sort hall_p">${data.movieType}</p>
            <p class="hall_line hall_p">${data.seatLine}</p>
            <p class="hall_column hall_p">${data.seatColumn}</p>
            <p class="hall_operate">
              <button type="reset" class="edit_hall">编辑</button>
              <button type="reset" class="delete_hall">删除</button>
            </p>
          </form>`

        var parent = $('.all_hall').eq(1);
        parent.append(str)
        $('#float_box').css({ "display": "none" })
        $('#new_hall_form').css({ "display": "none" })
        $('#edit_hall_form').css({ "display": "none" })
      }
    })
  })


}


/*
 * 功能：编辑影厅部分，取消编辑影厅
*/

$('#cancel_edit_hall').on("click", function () {
  $('#float_box').css({ "display": "none" })
  $('#new_hall_form').css({ "display": "none" })
  $('#edit_hall_form').css({ "display": "none" })
})

/*
 * 功能：修改当前影厅内容并提交
 */