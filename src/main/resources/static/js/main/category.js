/**
 * File dieu khien giao dien phan loai anh
 * @author DoanQuan
 * @description Xử lý dữ liệu hình ảnh trả về
 * @date 9/11/2019
 */

$(document).ready(function () {
    displayCategory();
    defaultImageLoading(1)
});

function displayCategory() {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: apiUrl.Config.local + "/getAllTreeTypes",
        data: {
            format: 'JSON'
        },
        dataType: "json",
        success: function (data) {
            let i = -1, length = data.length;
            while (++i < length) {
                var liElement = '';
                if (i == 0) {
                    liElement = liElement.concat('<li id="type_', data[i].id, '" class="active"><a href="#">', data[i].name, '</a></li>')
                }
                else {
                    liElement = liElement.concat('<li id="type_', data[i].id, '"><a href="#">', data[i].name, '</a></li>')
                };
                $("#bonsaiCategory ul").append(liElement)
            }

        },
        error: function (data) { }
    });
}

var now = 0

// Nhận click từ element đã được AJAX trả vể
$('#bonsaiCategory ul').on('click', 'li', function() {
    let id = this.id.slice(-1)
    defaultImageLoading(id)
});

function defaultImageLoading(id) {
    $.ajax({
		type: "GET",
		contentType: "application/json; charset=utf-8",
		url: apiUrl.Config.local + "/getImagesbyGroupId/" + id,
		data: {
			format: 'JSON'
		},
		cache: true,
		dataType: "json",
		async: false,
		success: function (data) {
            displayImages(data)
        },
		error: function (error) {
			console.error(error)
		}
	});
}


function displayImages(data) {
    let length = data.length;
    for (let i = 0; i < length; i++) {
        $('#total-img').html(length)
        if (data[i].link.includes(',')) {
            var arrays = data[i].link.split(",")
            // for (let j in arrays) {
                // Gán dữ liệu
                // $("#titleFrame").html(data[i].title)
            $("#img-from")
                .attr("src", 'img/bonsai-img/'.concat(arrays[0]))
                .attr('loading', 'auto')
                .attr('alt', data[i].title)
            // $("#img-to")
            //     .attr("src", 'img/bonsai-img/'.concat(data[i+1].link))
            //     .attr('loading', 'auto')
            //     .attr('alt', data[i+1].title)
            // $("#product").attr('title', data[i].summary)
            // Copy block cũ 
            var newel = $('#product:last').clone()
            // Ngăn không cho copy lại ảnh cuối cùng
            if (i == length - 1) {
                break;
            }
            // Thêm vào sau đó
            $(newel).insertAfter("#product:last");
            //     break
            // }
        }
        else {
            $("#img-from")
                    .attr("src", 'img/bonsai-img/'.concat(data[i].link))
                    .attr('loading', 'auto')
                    .attr('alt', data[i].title)
                // $("#img-to")
                //     .attr("src", 'img/bonsai-img/'.concat(data[i+1].link))
                //     .attr('loading', 'auto')
                //     .attr('alt', data[i+1].title)
                // $("#product").attr('title', data[i].summary)
                // Copy block cũ 
                var newel = $('#product:last').clone()
                // Ngăn không cho copy lại ảnh cuối cùng
                if (i == length - 1) {
                    break;
                }
                // Thêm vào sau đó
                $(newel).insertAfter("#product:last");
        }
    }
}