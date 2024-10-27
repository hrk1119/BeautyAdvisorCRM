const name = document.querySelector('#name');
const btn = document.querySelector('#btn');

//コンソールに表示
console.log(name);
console.log(btn);

btn.addEventListener('click', fetchServlet);        //btnがクリックされたとき、fetchServletを実行

function fetchServlet() {
    const data = {                                  //Servletに送信するデータ
        method: 'POST',
        headers: {
            'Content-Type': "text/plain"
        },
        body: name.value							//このbodyが変数dataの内容
    };

    fetch('./CheckInServlet', data)                   // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
        .then((response) => response.text())        //responseをjsonで受け取る
        .then((json) => {
            console.log(json);
            document.querySelector('#log').innerHTML = "<p>" + json + "様</p>";     //idがlogのelementの中にtableを作成
        });
}