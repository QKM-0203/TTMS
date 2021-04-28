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

/*
 * 功能：删除影厅
*/

$('.delete_hall').each(function () {
  $(this).on("click", function () {
    console.log('删除该按钮')
  })
})


/*
 * 功能：编辑当前影厅
*/

$('.edit_hall').each(function () {
  $(this).on("click", function () {
    console.log('编辑该按钮')
    $('#float_box').css({ "display": "block" })
    $('#new_hall_form').css({ "display": "none" })
    $('#edit_hall_form').css({ "display": "block" })
  })
})

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