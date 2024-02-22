## 問題1.

以下の要件に従って、特定の画面を表示する機能を持つハンドラメソッドを作成してください。

このハンドラメソッドでは、return文で指定された文字列を直接表示するように設定してください。

- ハンドラメソッドは `controller/HomeController` 内に、不足しているアノテーション等の記述を補完して作成してください。
- `Welcome to the Schedule Management System` というテキストは **見出し1** とします。
- `Go to Schedule List`というテキストのリンクが、`/question2/index`へ遷移するように設定してください。

### 動作確認

`http://localhost:8080/question1` へアクセスし、以下の画像の通りの表示となることを確認してください。

画像1



## 問題2.

簡易的なスケジュール管理リストを作成します。

まず、スケジュールの一覧を表示する画面を作成します。

`resouces/templates` 直下の `index.html` ファイルを使用し、以下の指示に従って必要なクラスとハンドラメソッドを作成してください。

`index.html` を改変してはいけません。

1. 必要なクラスの修正: `com.example.chapter01test（プロジェクト名）` パッケージ内にある`Schedule`クラスを修正してください。  
フィールド名や型は、`index.html`を参照して推測し、追加してください。  
コンストラクタ、アクセサも追加してください。
2. フィールドの定義:`controller`パッケージ内の`ScheduleController.java` にて、フィールドとして `Schedule` インスタンスを格納するリスト `scheduleList` を定義してください。  
`scheduleList` の初期値には以下のインスタンスを格納してください。
    
    
    | ID | Name(タスク名) | StartDateTime(開始日時) | Duration(所要時間) | Location(場所) |
    | --- | --- | --- | --- | --- |
    | 1 | Lunch | 2019/01/22 12:00:00 | 1.0 | Shibuya |
    | 2 | Meeting | 2019/01/22 15:00:00 | 3.0 | Shinjuku |
    | 3 | Dinner | 2019/01/22 19:00:00 | 8.0 | Kichijoji |
3. ハンドラメソッドの作成: `ScheduleController.java`にて、必要なハンドラメソッドを定義してください。  
必要であれば、適切なアノテーションやその他記述を追加してください。  
ビューに渡すデータとして、2.で定義した `scheduleList` を使用してください。

### 動作確認

`http://localhost:8080/question2/index` へアクセスし、以下の画像の通りの出力となることを確認してください。

画像2



## 問題3.

問題2で作成した一覧画面の **「detail」** ボタンをクリックすると、スケジュールの詳細を表示する画面に遷移します。

この機能を実装するために、`resouces/templates` 直下の `detail.html` ファイルを使用して、指定された表示に沿ったハンドラメソッドを`ScheduleController.java`に作成してください。

`detail.html` を改変してはいけません。

### 動作確認

ID:1の **「detail」** ボタンを押下し、`http://localhost:8080/question2/detail/1` へアクセスした時に以下の画像の通りの表示となることを確認してください。

画像3

↓

画像4



## 問題4.

問題2で作成した一覧画面の中央下部にある **「Search」** フォームを使用すると、ユーザーが入力したNameとLocationの文字列に部分一致するスケジュールを全て表示します。

この機能を実装するために、`resouces/templates` 直下の `result.html`ファイルを使用し、指定された表示に従って`ScheduleController.java`内に適切なハンドラメソッドを作成してください。

NameとLocationはそれぞれ入力は**必須ではありません** 。

また、これらのフィールドに**初期値は設定されていません**。

`result.html` を改変してはいけません。

### 動作確認

以下画像通りのの入力後 **「search」** ボタンを押下し、`http://localhost:8080/question2/search?name=Dinner&location=Kichijoji` へアクセスした時に以下の画像の通りの表示となることを確認してください。

画像5

↓

画像6



## 問題5.

問題2で作成した一覧画面の最下部にある **「Click here to register」** リンクをクリックすると、新規スケジュール登録画面に遷移します。

この機能を実装するために、`resouces/templates` 直下の `register.html`ファイルを使用し、以下の指示に従って必要なDTO(Data Transfer Object)クラスとハンドラメソッドを作成してください。

- 必要なDTOクラスは、`com.example.chapter01test(プロジェクト名).dto` パッケージ内の`ScheduleForm` クラスを修正してください。
- ハンドラメソッドは`ScheduleController.java`に作成してください。

`register.html` を改変してはいけません。

### 動作確認

**「Click here to register」** リンクをクリック し、 `http://localhost:8080/question2/register` へアクセスした時に以下の画像の通りの表示となることを確認してください。

画像7



## 問題6.

問題5で作成した登録画面から全ての入力必須項目を入力し、 **「Register」** ボタンをクリックすると、データが正常に登録された場合には以下の登録成功画面に遷移します。

この挙動を実装するために、`resouces/templates` 直下の `success.html`ファイルを用いて、以下の指示に沿ったハンドラメソッドを作成してください。

- ハンドラメソッドは `ScheduleController.java`に実装してください。
- 今回の実装では、IDを含む全ての項目の重複登録を許可します。
- また、今回はバリデーションの実装は必要ありません。

`success.html` を改変してはいけません。

### 動作確認

全ての必須項目を入力後 **「Register」** ボタンをクリックすると、`http://localhost:8080/question2/add`へアクセスし以下の画像の通りの表示となることを確認してください。

また登録成功後、画面下部にあるリンクから一覧画面に戻り、項目を入力したスケジュールがテーブルに正常に追加されていることを確認してください。

画像8

