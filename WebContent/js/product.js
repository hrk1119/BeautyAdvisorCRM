//product追加
const pBtnAdd = document.querySelector('#p-btn-add');
pBtnAdd.addEventListener('click', (event) => {
    event.preventDefault();
    const colors = document.querySelector('#colors');
    const length = document.querySelectorAll('.product_color').length;
    console.log(length);
    const newDiv = document.createElement('div');

    newDiv.innerHTML += `<p class="col-11 mx-auto my-3">${length + 1}つ目</p>
    <table class="table search col-11 mx-auto product_color">
        <tr>
            <th class="start">JANコード</th>
            <td class="start"><input type="text" name="product_code${length}"
                    class="col-8 form-control validate[required]"
                    placeholder="JANコードを入力してください">
            </td>
        </tr>
        <tr>
            <th>カラー番号</th>
            <td><input type="text" name="color_num${length}"
                    class="col-8 form-control validate[required]" placeholder="RD300"></td>
        </tr>
        <tr>
            <th>カラー名</th>
            <td><input type="text" name="color_name${length}"
                    class="col-8 form-control validate[required]" placeholder="レッド"></td>
        </tr>
        <tr>
            <th>写真</th>
            <td><input type="file" name="photo${length}" class="col-8">
            </td>
        </tr>
        <tr>
            <th>カラーカテゴリ</th>
            <td><select name="color_category${length}" class="form-control col-6">
                    <option value="0">選択してください</option>
                    <option value="1">ローズ</option>
                    <option value="2">ピンク</option>
                    <option value="3">レッド</option>
                    <option value="4">ブラウン</option>
                    <option value="5">ベージュ</option>
                    <option value="6">オレンジ</option>
                    <option value="7">バイオレット</option>
                    <option value="8">グリーン</option>
                    <option value="9">ブルー</option>
                    <option value="10">イエロー</option>
                    <option value="11">ゴールド</option>
                    <option value="12">シルバー</option>
                    <option value="13">グレー</option>
                    <option value="14">マルチカラー</option>
                </select>
                <p>※ポイントメイクのみ</p>
            </td>
        </tr>
        <tr>
            <th>パーソナルカラー</th>
            <td>
                <div class="checkbox">
                    <label class="p"><input type="checkbox" name="pc_color${length}"
                            value="1">イエローベース</label>
                    <label class="p"><input type="checkbox" name="pc_color${length}" value="2"
                            class="ml-3">ブルーベース</label>
                    <label class="p"><input type="checkbox" name="pc_color${length}" value="3"
                            class="ml-3">不明</label>
                </div>
            </td>
        </tr>
    </table>`;
    colors.appendChild(newDiv);
});

