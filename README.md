## 試験を始める前に必ずやること!!
以下の手順に沿って操作を行ってください。
1. VSCodeで、プロジェクト直下にある`.github`フォルダの中の `/workflows/classroom.yml`というファイルを開く
2. 1で開いたファイルの中身を以下の内容に丸ごと書き換える
   1. ミスしないように必ず**コピペ**で書き換えること
```yml
name: GitHub Classroom Workflow

on:
  - push
  - workflow_dispatch

permissions:
  checks: write
  actions: read
  contents: read

jobs:
  build:
    name: Autograding
    runs-on: ubuntu-latest
    if: github.actor != 'github-classroom[bot]'
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '21'
      - uses: education/autograding@v1
```
3. 書き換えが完了したらこのファイルを閉じる
   1. 以降このファイルは絶対に操作しないこと（何か誤操作があるとソースコードが動かなくなります）

## 問題1.

以下の要件に従って、特定の画面を表示する機能を持つハンドラメソッドを作成してください。

このハンドラメソッドでは、return文で指定された文字列を直接表示するように設定してください。

- ハンドラメソッドは `controller/HomeController` 内に、不足しているアノテーション等の記述を補完して作成してください。
- `Welcome to the Schedule Management System` というテキストは **見出し1** とします。
- `Go to Schedule List`というテキストのリンクが、`/question2/index`へ遷移するように設定してください。

### 動作確認

`http://localhost:8080/question1` へアクセスし、以下の画像の通りの表示となることを確認してください。

![springboot_chapter01test_01](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/b1fb7219-c3b3-4a31-8e41-9f88b44c7acb)




## 問題2.

簡易的なスケジュール管理リストを作成します。

まず、スケジュールの一覧を表示する画面を作成します。

`resouces/templates` 直下の `index.html` ファイルを使用し、以下の指示に従って必要なクラスとハンドラメソッドを作成してください。

`index.html` を改変してはいけません。

1. 必要なクラスの修正: `com.example.chapter01test（プロジェクト名）` パッケージ内にある`Schedule`クラスを修正してください。
フィールド名や型は、`index.html`を参照して推測し、追加してください。
コンストラクタ、アクセサも追加してください。
2. フィールドの定義:`controller`パッケージ内の`ScheduleController.java` には、すでにフィールドとして `Schedule` インスタンスを格納する空のArrayList `scheduleList` が宣言されています。
この`scheduleList` の初期値に、以下のインスタンスを格納するように修正してください。


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

![スクリーンショット 2024-02-20 134542](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/ff212af8-338e-449c-af56-18aa50ed7990)



## 問題3.

問題2で作成した一覧画面の **「detail」** ボタンをクリックすると、スケジュールの詳細を表示する画面に遷移します。

この機能を実装するために、`resouces/templates` 直下の `detail.html` ファイルを使用して、指定された表示に沿ったハンドラメソッドを`ScheduleController.java`に作成してください。

`detail.html` を改変してはいけません。

### 動作確認

ID:1の **「detail」** ボタンを押下し、`http://localhost:8080/question2/detail/1` へアクセスした時に以下の画像の通りの表示となることを確認してください。

![スクリーンショット 2024-02-15 105335](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/4a46c997-4bde-452b-b0e3-c263a0a2b4c1)

↓

![スクリーンショット 2024-02-10 135033](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/fa098452-266e-4496-b544-b149b4091c22)



## 問題4.

問題2で作成した一覧画面の中央下部にある **「Search」** フォームを使用すると、ユーザーが入力したNameとLocationの文字列に部分一致するスケジュールを全て表示します。

この機能を実装するために、`resouces/templates` 直下の `result.html`ファイルを使用し、指定された表示に従って`ScheduleController.java`内に適切なハンドラメソッドを作成してください。

NameとLocationはそれぞれ入力は**必須ではありません** 。

また、これらのフィールドに**初期値は設定されていません**。

`result.html` を改変してはいけません。

### 動作確認1

以下画像通りのの入力後 **「search」** ボタンを押下し、`http://localhost:8080/question2/search?name=Dinner&location=Kichijoji` へアクセスした時に以下の画像の通りの表示となることを確認してください。

![スクリーンショット 2024-02-10 141318](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/499a98c8-5387-46b0-a441-a1784654f2a3)

↓

![スクリーンショット 2024-02-20 140602](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/73151880-26f0-47de-a809-09a26ab8e22a)


### 動作確認2

ブラウザのアドレスバーに **「`http://localhost:8080/question2/search?name=Lu`」** 入力後、以下の画像の通りの表示となることを確認してください。


### 動作確認3

ブラウザのアドレスバーに **「`http://localhost:8080/question2/search?location=Shi`」** 入力後、以下の画像の通りの表示となることを確認してください。




## 問題5.

問題2で作成した一覧画面の最下部にある **「Click here to register」** リンクをクリックすると、新規スケジュール登録画面に遷移します。

この機能を実装するために、`resouces/templates` 直下の `register.html`ファイルを使用し、以下の指示に従って必要なDTO(Data Transfer Object)クラスとハンドラメソッドを作成してください。

- 必要なDTOクラスは、`com.example.chapter01test(プロジェクト名).dto` パッケージ内の`ScheduleForm` クラスを修正してください。
- ハンドラメソッドは`ScheduleController.java`に作成してください。

`register.html` を改変してはいけません。

### 動作確認

**「Click here to register」** リンクをクリック し、 `http://localhost:8080/question2/register` へアクセスした時に以下の画像の通りの表示となることを確認してください。

![スクリーンショット 2024-02-10 142854](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/9ef16dab-13b1-48dc-b64d-71011b8ae05e)




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

![スクリーンショット 2024-02-10 143907](https://github.com/SLStudioLesson/SpringBootChapter1assessment/assets/98870955/772cfd53-e373-45fb-acbe-2bec4e872d1d)

