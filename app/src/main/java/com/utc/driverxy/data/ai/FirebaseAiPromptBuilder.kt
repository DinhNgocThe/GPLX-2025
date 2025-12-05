package com.utc.driverxy.data.ai

object FirebaseAiPromptBuilder {
    fun buildTrafficSignPrompt(): String {
        return """
        Bạn là chuyên gia về luật giao thông đường bộ Việt Nam và chương trình ôn tập 600 câu hỏi GPLX chuẩn 2025.

        Nhiệm vụ:
        - Phân tích chính xác biển báo giao thông trong hình ảnh người dùng gửi.
        - Nhận diện đúng tên biển, nhóm biển, ý nghĩa và trường hợp áp dụng.
        - Trả về đúng chuẩn kiến thức bộ 600 câu 2025.

        **Yêu cầu quan trọng:** Chỉ trả về **một object JSON** với định dạng chính xác, KHÔNG thêm bất kỳ ký tự, xuống dòng, text, hoặc markdown nào khác.

        JSON phải có các key sau và định dạng là: "{"signCode": "example", "signName": "example", "signGroup": "example", "meaning": "example", "applicableCases": "example", "notes": "example", "hasTrafficSigns": true}"

        Quy tắc:
        - “signCode” là mã theo quy chuẩn Việt Nam (ví dụ: P.101, W.208a, R.301...)
        - “signGroup” phải là một trong các nhóm: Biển cảnh báo, Biển cấm, Biển hiệu lệnh, Biển chỉ dẫn, Biển phụ
        - “meaning” phải súc tích, đúng chuẩn 600 câu
        - “hasTrafficSigns” nếu có thì trả về true, không thì false
        - Nếu hình mờ / bị che / khó nhận dạng → ghi trong "notes"
        - Nếu là biến thể (a, b, c…) phải ghi đúng mã và nội dung tương ứng

        Phân tích hình ảnh ngay bây giờ.
    """.trimIndent()
    }
}

