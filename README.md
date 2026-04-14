## Phần 1: Phân tích & Đề xuất giải pháp

### 1.1. Xác định Input/Output
* **Input:** Đối tượng `RegisterRequestDto` nhận từ phía Client, bao gồm:
    * `username`: Tên đăng nhập.
    * `password`: Mật khẩu người dùng nhập.
    * `confirmPassword`: Mật khẩu nhập lại để xác nhận.
* **Output:** * Trạng thái hợp lệ (Boolean: true/false).
    * Thông báo lỗi (Error Message) được gắn vào trường `confirmPassword` nếu dữ liệu không khớp.

### 1.2. Các giải pháp kỹ thuật đề xuất

#### Giải pháp 1: Xử lý thủ công tại Controller/Service (Imperative Approach)
Sử dụng các câu lệnh điều kiện `if-else` ngay sau khi nhận dữ liệu trong Controller hoặc trước khi xử lý logic ở Service.

* **Cách hoạt động:** Sau bước `@Valid`, lập trình viên viết thêm logic so sánh `dto.getPassword().equals(dto.getConfirmPassword())`. Nếu sai, thủ công thêm lỗi vào `BindingResult`.

#### Giải pháp 2: Custom Validation ở Cấp độ Class (Declarative Approach)
Tạo một Annotation tùy chỉnh (ví dụ: `@FieldsValueMatch`) và gắn trực tiếp lên đầu Class DTO.

* **Cách hoạt động:** Sử dụng cơ chế Bean Validation của Java (Jakarta Validation) để tự động hóa việc kiểm tra ngay khi Spring thực hiện bind dữ liệu vào Object. Logic so sánh được tách riêng ra một lớp Validator chuyên biệt.

---

## Phần 2: So sánh & Lựa chọn giải pháp

Dưới đây là bảng so sánh chi tiết giữa hai giải pháp dựa trên các tiêu chí kỹ thuật:

| Tiêu chí | Giải pháp 1: If-else trong Controller | Giải pháp 2: Class-Level Annotation |
| :--- | :--- | :--- |
| **Boilerplate Code** | Cao (Lặp lại logic mỗi khi có Form mới) | Thấp (Chỉ cần gắn 1 dòng Annotation) |
| **Khả năng tái sử dụng** | Không (Phải viết lại cho Form Đổi mật khẩu,...) | Rất cao (Dùng chung cho mọi cặp trường cần khớp) |
| **Separation of Concerns** | Thấp (Controller bị dính logic nghiệp vụ) | Cao (Tách biệt hoàn toàn logic kiểm tra dữ liệu) |
| **Trải nghiệm UX** | Khó điều hướng lỗi về đúng ô Input | Dễ dàng điều hướng lỗi về đúng trường cụ thể |
| **Độ phức tạp ban đầu** | Thấp (Dễ viết cho người mới) | Trung bình (Cần hiểu về Custom Annotation) |

### CHỐT GIẢI PHÁP: Giải pháp 2 (Class-Level Annotation)

**Lý do lựa chọn:**
Mặc dù Giải pháp 2 yêu cầu thiết lập ban đầu phức tạp hơn một chút, nhưng nó mang lại sự sạch sẽ tuyệt đối cho mã nguồn (Clean Code). Đặc biệt trong môi trường FinTech hoặc các hệ thống mạng xã hội lớn, việc tuân thủ nguyên tắc **Separation of Concerns** (Tách biệt các mối quan tâm) và khả năng tái sử dụng mã nguồn là ưu tiên hàng đầu để giảm thiểu lỗi hệ thống và dễ dàng bảo trì.
