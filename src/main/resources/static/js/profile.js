
// 구독자 정보 보기
document.querySelector("#subscribeBtn").onclick = (e) => {
	e.preventDefault();
	document.querySelector(".modal-follow").style.display = "flex";

	// ajax 통신후에 json 리턴 -> javascript 오브젝트 변경 => for문 돌면서 뿌리기

	console.log("===============")
	console.log(e);


	let userId = $("#userId").val();
	// res에 괄호를 넣는 이유는 파라메타가 2개이상일 때 넣는다.
	$.ajax({

		// http 8080 없어도 되는 이유 같은 도메인을 호출하는거여서, 도메인이 다른걸 막을 때 cors정책이 걸림
		url: `/user/${userId}/follow`,

	}).done(res => {

		console.log(1, res);
		$("#follow_list").empty();

		res.data.forEach((u) => {
			console.log(2, u)
			let item = makeSubscribeInfo(u);
			$("#follow_list").append(item);
		});

	}).fail(error => {
		alert("오류" + error)
	});


};

function makeSubscribeInfo(u) {
	let item = `<div class="follower__item" id="follow-${u.userId}">`;
	item += `<div class="follower__img">`;
	item += `<img src="/images/profile.jpeg" alt="">`;
	item += `</div>`;
	item += `<div class="follower__text">`;
	item += `<h2>${u.username}</h2>`;
	item += `</div>`;
	item += `<div class="follower__btn">`;
	if(!u.equalState){
		if(u.followState){
			item += `<button class="cta blue" onclick="followOrUnFollowModal(${u.userId})">구독취소</button>`;	
		}else{
			item += `<button class="cta" onclick="followOrUnFollowModal(${u.userId})">구독하기</button>`;
		}	
	}
	item += `</div>`;
	item += `</div>`;

	return item;

}

function followOrUnFollowModal(userId){
	let text = $(`#follow-${userId} button`).text();
	
	if(text === "구독취소"){
		$.ajax({
			type: "DELETE",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow-${userId} button`).text("구독하기");
			$(`#follow-${userId} button`).toggleClass("blue");
		});
	}else{
		$.ajax({
			type: "POST",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow-${userId} button`).text("구독취소");
			$(`#follow-${userId} button`).toggleClass("blue");
		});
	}
}


function followOrUnFollowProfile(userId){
	let text = $(`#follow_profile_btn`).text();
	
	if(text === "구독취소"){
		$.ajax({
			type: "DELETE",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow_profile_btn`).text("구독하기");
			$(`#follow_profile_btn`).toggleClass("blue");
		});
	}else{
		$.ajax({
			type: "POST",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow_profile_btn`).text("구독취소");
			$(`#follow_profile_btn`).toggleClass("blue");
		});
	}
}


/*$("#follow-1") //안에있는값을 바꾸는 제이쿼리가 있음
$("#follow-1 button").클래스명변경("cta");

$("#follow-1 button").text("구독취소");*/



function closeFollow() {
	document.querySelector(".modal-follow").style.display = "none";
}
document.querySelector(".modal-follow").addEventListener("click", (e) => {
	if (e.target.tagName !== "BUTTON") {
		document.querySelector(".modal-follow").style.display = "none";
	}
});
function popup(obj) {
	console.log(obj);
	document.querySelector(obj).style.display = "flex";
}
function closePopup(obj) {
	console.log(2);
	document.querySelector(obj).style.display = "none";
}
document.querySelector(".modal-info").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-info").style.display = "none";
	}
});
document.querySelector(".modal-image").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-image").style.display = "none";
	}
});

/*function clickFollow(e) {
	console.log(e);
	let _btn = e;
	console.log(_btn.textContent);
	if (_btn.textContent === "구독취소") {
		_btn.textContent = "구독하기";
		_btn.style.backgroundColor = "#fff";
		_btn.style.color = "#000";
		_btn.style.border = "1px solid #ddd";
	} else {
		_btn.textContent = "구독취소";
		_btn.style.backgroundColor = "#0095f6";
		_btn.style.color = "#fff";
		_btn.style.border = "0";
	}
}
*/