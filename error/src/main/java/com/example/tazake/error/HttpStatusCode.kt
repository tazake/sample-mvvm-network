package com.example.tazake.error


/**
 * HTTPステータスコード列挙型
 *
 * @param statusCode HTTPステータスコード
 *
 * @property DEFAULT デフォルト
 * @property CONTINUE 継続 クライアントはリクエストを継続できる。
 * @property SWITCHING_PROTOCOLS プロトコル切り替え サーバはリクエストを理解し、遂行のためにプロトコルの切り替えを要求している
 * @property PROCESSING 処理中 WebDAVの拡張ステータスコード。処理が継続して行われていることを示す。
 * @property OK OK リクエストは成功し、レスポンスとともに要求に応じた情報が返される。
 * @property CREATED 作成 リクエストは完了し、新たに作成されたリソースのURIが返される。
 * @property ACCEPTED 受理 リクエストは受理されたが、処理は完了していない。
 * @property NON_AUTHORITATIVE_INFORMATION 信頼できない情報 オリジナルのデータではなく、ローカルやプロキシ等からの情報であることを示す。
 * @property NO_CONTENT 内容なし リクエストを受理したが、返すべきレスポンスエンティティが存在しない場合に返される。
 * @property RESET_CONTENT 内容のリセット リクエストを受理し、ユーザエージェントの画面をリセットする場合に返される。
 * @property PARTIAL_CONTENT 部分的内容 部分的GETリクエストを受理したときに、返される。
 * @property MULTI_STATUS 複数のステータス WebDAVの拡張ステータスコード。
 * @property IM_USED IM使用 Delta encoding in HTTPの拡張ステータスコード。
 * @property MULTIPLE_CHOICES 複数の選択 リクエストしたリソースが複数存在し、ユーザやユーザーエージェントに選択肢を提示するときに返される。
 * @property HTTP_STATUS_CODE_MOVED_PERMANENTLY 恒久的に移動した リクエストしたリソースが恒久的に移動されているときに返される。Location:ヘッダに移動先のURLが示されている。
 * @property FOUND 発見した リクエストしたリソースが一時的に移動されているときに返される。Location:ヘッダに移動先のURLが示されている。
 * @property SEE_OTHER 他を参照せよ リクエストに対するレスポンスが他のURLに存在するときに返される。Location:ヘッダに移動先のURLが示されている。
 * @property NOT_MODIFIED 未更新 リクエストしたリソースは更新されていないことを示す。
 * @property USE_PROXY プロキシを使用せよ レスポンスのLocation:ヘッダに示されるプロキシを使用してリクエストを行わなければならないことを示す。
 * @property UNUSED 将来のために予約されている。ステータスコードは前のバージョンの仕様書では使われていたが、もはや使われておらず、将来のために予約されているとされる。
 * @property TEMPORARY_REDIRECT 一時的リダイレクト リクエストしたリソースは一時的に移動されているときに返される。Location:ヘッダに移動先のURLが示されている。
 * @property BAD_REQUEST リクエストが不正である 定義されていないメソッドを使うなど、クライアントのリクエストがおかしい場合に返される。
 * @property UNAUTHORIZED 認証が必要である Basic認証やDigest認証などを行うときに使用される。
 * @property PAYMENT_REQUIRED 支払いが必要である 現在は実装されておらず、将来のために予約されているとされる。
 * @property FORBIDDEN 禁止されている リソースにアクセスすることを拒否された。
 * @property NOT_FOUND 未検出 リソースが見つからなかった。
 * @property METHOD_NOT_ALLOWED 許可されていないメソッド 許可されていないメソッドを使用しようとした。
 * @property NOT_ACCEPTABLE 受理できない Accept関連のヘッダに受理できない内容が含まれている場合に返される。
 * @property PROXY_AUTHENTICATION_REQUIRED プロキシ認証が必要である プロキシの認証が必要な場合に返される。
 * @property REQUEST_TIME_OUT リクエストタイムアウト リクエストが時間以内に完了していない場合に返される。
 * @property CONFLICT 矛盾 要求は現在のリソースと矛盾するので完了できない。
 * @property GONE 消滅した。ファイルは恒久的に移動した。
 * @property LENGTH_REQUIRED 長さが必要 Content-Lengthヘッダがないのでサーバーがアクセスを拒否した場合に返される。
 * @property PRECONDITION_FAILED 前提条件で失敗した 前提条件が偽だった場合に返される。
 * @property REQUEST_ENTITY_TOO_LARGE リクエストエンティティが大きすぎる リクエストエンティティがサーバの許容範囲を超えている場合に返す。
 * @property REQUEST_URI_TOO_LONG リクエストURIが大きすぎる URIが長過ぎるのでサーバが処理を拒否した場合に返す。
 * @property UNSUPPORTED_MEDIA_TYPE サポートしていないメディアタイプ 指定されたメディアタイプがサーバでサポートされていない場合に返す。
 * @property REQUESTED_RANGE_NOT_SATISFIABLE リクエストしたレンジは範囲外にある 実ファイルのサイズを超えるデータを要求した。
 * @property EXPECTATION_FAILED Expectヘッダによる拡張が失敗 その拡張はレスポンスできない。またはプロキシサーバは、次に到達するサーバがレスポンスできないと判断している。
 * @property IMA_TEAPOT 私はティーポット HTCPCP/1.0の拡張ステータスコード。
 * @property UNPROCESSABLE_ENTITY 処理できないエンティティ WebDAVの拡張ステータスコード。
 * @property LOCKED ロックされている WebDAVの拡張ステータスコード。リクエストしたリソースがロックされている場合に返す。
 * @property FAILED_DEPENDENCY 依存関係で失敗 WebDAVの拡張ステータスコード。
 * @property UPGRADE_REQUIRED アップグレード要求 Upgrading to TLS Within HTTP/1.1の拡張ステータスコード。
 * @property INTERNAL_SERVER_ERROR サーバ内部エラー サーバ内部にエラーが発生した場合に返される。
 * @property NOT_IMPLEMENTED 実装されていない 実装されていないメソッドを使用した。
 * @property BAD_GATEWAY 不正なゲートウェイ ゲートウェイ・プロキシサーバは不正な要求を受け取り、これを拒否した。
 * @property SERVICE_UNAVAILABLE サービス利用不可 サービスが一時的に過負荷やメンテナンスで使用不可能である。
 * @property GATEWAY_TIME_OUT ゲートウェイタイムアウト ゲートウェイ・プロキシサーバはURIから推測されるサーバからの適切なレスポンスがなくタイムアウトした。
 * @property HTTP_VERSION_NOT_SUPPORTED サポートしていないHTTPバージョン リクエストがサポートされていないHTTPバージョンである場合に返される。
 * @property VARIANT_ALSO_NEGOTIATES Transparent Content Negotiation in HTTPで定義されている拡張ステータスコード。
 * @property INSUFFICIENT_STORAGE 容量不足 WebDAVの拡張ステータスコード。リクエストを処理するために必要なストレージの容量が足りない場合に返される。
 * @property BAND_WIDTH_LIMIT_EXCEEDED 帯域幅制限超過 そのサーバに設定されている帯域幅（転送量）を使い切った場合に返される。
 * @property NOT_EXTENDED 拡張できない An HTTP Extension Frameworkで定義されている拡張ステータスコード。
 */
enum class HttpStatusCode(val statusCode: Int) {
    DEFAULT(0),
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTHORITATIVE_INFORMATION(203),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),
    MULTI_STATUS(207),
    IM_USED(226),
    MULTIPLE_CHOICES(300),
    HTTP_STATUS_CODE_MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    NOT_MODIFIED(304),
    USE_PROXY(305),
    UNUSED(306),
    TEMPORARY_REDIRECT(307),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    PROXY_AUTHENTICATION_REQUIRED(407),
    REQUEST_TIME_OUT(408),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PRECONDITION_FAILED(412),
    REQUEST_ENTITY_TOO_LARGE(413),
    REQUEST_URI_TOO_LONG(414),
    UNSUPPORTED_MEDIA_TYPE(415),
    REQUESTED_RANGE_NOT_SATISFIABLE(416),
    EXPECTATION_FAILED(417),
    IMA_TEAPOT(418),
    UNPROCESSABLE_ENTITY(422),
    LOCKED(423),
    FAILED_DEPENDENCY(424),
    UPGRADE_REQUIRED(426),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIME_OUT(504),
    HTTP_VERSION_NOT_SUPPORTED(505),
    VARIANT_ALSO_NEGOTIATES(506),
    INSUFFICIENT_STORAGE(507),
    BAND_WIDTH_LIMIT_EXCEEDED(509),
    NOT_EXTENDED(510),
    UNKNOWN(-1);

    companion object {
        fun typeOf(statusCode: Int): HttpStatusCode {
            return try {
                values().first { it.statusCode == statusCode }
            } catch (e: Exception) {
                UNKNOWN
            }
        }
    }

}

