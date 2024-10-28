let dialog = document.getElementById('link');
let linkBtn = document.getElementById('btn-link');

linkBtn.addEventListener("click", (e) => {
	dialog.showModal();
});

let copyBtn = document.getElementById('copy-btn');
copyBtn.addEventListener("click", (e) => {
	let linkUrl = document.getElementById('linkUrl').innerText;
	navigator.clipboard.writeText(linkUrl).then(() => {
		alert("링크가 복사되었습니다!");
	});
});

let closeBtn = document.getElementById('close-btn');
closeBtn.addEventListener("click", (e) => {
	dialog.close();
});