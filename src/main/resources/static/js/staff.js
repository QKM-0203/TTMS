
 //  所属板块：管理员列表
 // 功能：修改css
 //      使用动画所用的js
 //      除去提交form表单所用js
 //


 //
 // 功能：点击新增管理员按钮，出现新增管理员的弹框


$('#new_staff').on("click", function () {
  $('#float_staff').css({ "display": "block" })
  $('#new_staff_form').css({ "display": "block" })
  $('#edit_staff_form').css({ "display": "none" })
})


 // 功能：取消新增管理员弹框


$('#cancel_new_staff').on("click", function () {
  $('#float_staff').css({ "display": "none" })
  $('#new_staff_form').css({ "display": "none" })
  $('#edit_staff_form').css({ "display": "none" })
})



  // 功能：编辑当前影厅


$('.edit_staff').each(function () {
  $(this).on("click", function () {
    console.log('编辑该按钮')
    $('#float_staff').css({ "display": "block" })
    $('#new_staff_form').css({ "display": "none" })
    $('#edit_staff_form').css({ "display": "block" })
  })
})



 // 功能：取消编辑管理员


$('#cancel_edit_staff').on("click", function () {
  $('#float_staff').css({ "display": "none" })
  $('#new_staff_form').css({ "display": "none" })
  $('#edit_staff_form').css({ "display": "none" })
})