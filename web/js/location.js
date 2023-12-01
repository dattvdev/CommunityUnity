
            var jsonData; // Định nghĩa biến để lưu dữ liệu JSON

            fetch('./js/local.json') // Tải tệp JSON từ URL (hoặc tệp cục bộ)
                    .then(response => response.json())
                    .then(data => {
                        // Gán dữ liệu JSON vào biến jsonData
                        jsonData = data;

                        populateDropdown();
                    
                    })
                    .catch(error => {
                        console.error('Lỗi khi đọc tệp JSON:', error);
                    });

            function populateDropdown() {

                var provinceDropdown = document.getElementById("province");
                var districtDropdown = document.getElementById("district");
                var wardDropdown = document.getElementById("ward");

                // Điền dữ liệu vào dropdown tỉnh/thành phố
                jsonData.forEach(function (province) {
                    var option = document.createElement("option");
                    option.value = province.name;
                    option.textContent = province.name;
                    provinceDropdown.appendChild(option);
                });

                // Lắng nghe sự kiện thay đổi dropdown tỉnh/thành phố
                provinceDropdown.addEventListener("change", function () {
                    // Xóa tất cả tùy chọn quận/huyện và xã/phường hiện có
                    districtDropdown.innerHTML = '<option value="">Chọn quận/huyện</option>';
                    wardDropdown.innerHTML = '<option value="">Chọn xã/phường</option>';

                    var selectedProvinceId = provinceDropdown.value;

                    // Lọc danh sách quận/huyện dựa trên tỉnh/thành phố được chọn
                    var selectedProvince = jsonData.find(function (province) {
                        return province.name === selectedProvinceId;
                    });

                    if (selectedProvince && selectedProvince.districts) {
                        selectedProvince.districts.forEach(function (district) {
                            var option = document.createElement("option");
                            option.value = district.name;
                            option.textContent = district.name;
                            districtDropdown.appendChild(option);
                        });
                    }
                });


                // Lắng nghe sự kiện thay đổi dropdown quận/huyện
                districtDropdown.addEventListener("change", function () {
                    // Xóa tất cả tùy chọn xã/phường hiện có
                    wardDropdown.innerHTML = '<option value="">Chọn xã/phường</option>';

                    var selectedDistrictId = districtDropdown.value;

                    // Lọc danh sách xã/phường dựa trên quận/huyện được chọn
                    var selectedDistrict = null;

                    jsonData.forEach(function (province) {
                        var foundDistrict = province.districts.find(function (district) {
                            return district.name === selectedDistrictId;
                        });

                        if (foundDistrict) {
                            selectedDistrict = foundDistrict;
                        }
                    });

                    if (selectedDistrict && selectedDistrict.wards) {
                        selectedDistrict.wards.forEach(function (ward) {
                            var option = document.createElement("option");
                            option.value = ward.name;
                            option.textContent = ward.name;
                            wardDropdown.appendChild(option);
                        });
                    }
                });
            }

 