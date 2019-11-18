/**
 * File dieu khien giao dien trang chu
 * @author DoanQuan
 * @description Xử lý dữ liệu hình ảnh trả về
 * @date 9/11/2019
 */

$(document).ready(function () {
	arrangeimagesHome();
});

function arrangeimagesHome() {
	$.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: apiUrl.Config.local + '/getAllImages',
		data: {
			format: 'JSON'
		},
		cache: true,
		dataType: "json",
		async: false,
		success: function (data) {
			let length = data.length;
			for (let i = 0; i < length; i++) {
				if (data[i].link.includes(',')) {
					var arrays = data[i].link.split(",")
					for (let j in arrays) {
						// Gán dữ liệu
						$("#titleFrame").html(data[i].title)
						$("#imgFrame")
							.attr("src", 'img/bonsai-img/'.concat(arrays[0]))
							.attr('loading', 'auto')
							.attr('alt', data[i].title)
						$("#product").attr('title', data[i].summary)
						// Copy block cũ 
						var newel = $('#product:last').clone()
						// Ngăn không cho copy lại ảnh cuối cùng
						if (i == length - 1) {
							break;
						}
						// Thêm vào sau đó
						$(newel).insertAfter("#product:last");
						break
					}
				}
				else {
					$("#titleFrame").html(data[i].title)
					$("#imgFrame")
						.attr("src", 'img/bonsai-img/'.concat(data[i].link))
						.attr('loading', 'auto')
						.attr('alt', data[i].title)
					$("#product").attr('title', data[i].summary)
					var newel = $('#product:last').clone()
					// Ngăn không cho copy lại ảnh cuối cùng
					if (i == length - 1) {
						break;
					}
					$(newel).insertAfter("#product:last");
				}
			}

		},
		error: function (error) {
			console.error(error)
		}
	});

}

/* Set cache AJAX
beforeSend: function () {
	if (localCache.exist(url)) {
		doSomething(localCache.get(url));
		return false;
	}
	return true;
},
complete: function (jqXHR, textStatus) {
	localCache.set(url, jqXHR, doSomething);
},
*/