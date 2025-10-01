/* 도서 등록 */
const bookImage = document.getElementById('bookImage');
const preview = document.getElementById('preview');

bookImage.addEventListener('change', function (e) {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (event) {
            preview.src = event.target.result;
        }
        reader.readAsDataURL(file);
    } else {
        preview.src = 'https://via.placeholder.com/150x200?text=이미지+없음';
    }
});

function resetForm() {
    document.getElementById('bookForm').reset();
    preview.src = 'https://via.placeholder.com/150x200?text=이미지+없음';
}

document.getElementById('bookForm').addEventListener('submit', function (e) {
    e.preventDefault();
    alert('도서가 등록되었습니다 (추후 서버 구현)');
});