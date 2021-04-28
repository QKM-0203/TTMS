/**
 * 功能：处理全局需用到的js,除去表单提交
 */

$('.menu_li').each(function (index) {
  $(this).on('click', function () {
    $('.menu_li').each(function () {
      $(this).css("background-color", "rgba(247, 247, 247)");
    })
    $(this).css({ "background-color": "rgba(243, 242, 242)" })

    $('.right_box').each(function () {
      $(this).css("display", "none");
    })

    var element = $('.right_box')[index]
    console.log(element)
    $(element).css("display", "block")
  })
})
