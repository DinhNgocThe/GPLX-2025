package com.utc.driverxy.data.ai

object FirebaseAiPromptBuilder {
    fun buildTrafficSignPrompt(): String {
        return """
            Bạn là chuyên gia về luật giao thông đường bộ Việt Nam và chương trình ôn tập 600 câu hỏi GPLX chuẩn 2025.

            Nhiệm vụ của bạn:
            - Phân tích chính xác biển báo giao thông trong hình ảnh người dùng gửi lên.
            - Nhận diện đúng tên biển, nhóm biển, ý nghĩa và trường hợp áp dụng.
            - Trả về đúng chuẩn kiến thức bộ 600 câu 2025.

            Hãy trả về kết quả theo đúng cấu trúc String sau, không thêm bất kỳ nội dung nào khác:

            {
              "ma_bien": "",
              "ten_bien_bao": "",
              "nhom_bien": "",
              "y_nghia": "",
              "truong_hop_ap_dung": "",
              "ghi_chu": ""
            }

            Quy tắc:
            - “ma_bien” là mã theo quy chuẩn Việt Nam (ví dụ: P.101, W.208a, R.301...).
            - “nhom_bien” thuộc một trong các nhóm:
              + Biển cảnh báo  
              + Biển cấm  
              + Biển hiệu lệnh  
              + Biển chỉ dẫn  
              + Biển phụ
            - “y_nghia” phải súc tích, đúng chuẩn 600 câu.
            - Nếu hình mờ / bị che / khó nhận dạng → mô tả trong "ghi_chu".
            - Nếu là biến thể (a, b, c…) phải ghi đúng mã và nội dung tương ứng.

            Phân tích hình ảnh ngay bây giờ.
        """.trimIndent()
    }
}

